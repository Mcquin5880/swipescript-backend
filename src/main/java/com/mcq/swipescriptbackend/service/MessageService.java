package com.mcq.swipescriptbackend.service;

import com.mcq.swipescriptbackend.dto.CreateMessageRequestDto;
import com.mcq.swipescriptbackend.dto.MessageDto;
import com.mcq.swipescriptbackend.dto.helpers.MessageParams;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Message;
import com.mcq.swipescriptbackend.entity.Photo;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final AppUserRepository appUserRepository;

    public MessageDto sendMessage(CreateMessageRequestDto request, String senderUsername) {

        AppUser sender = appUserRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderUsername));
        AppUser recipient = appUserRepository.findByUsername(request.getRecipientUsername())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found: " + request.getRecipientUsername()));

        if (sender.getUsername().equals(recipient.getUsername())) {
            throw new IllegalArgumentException("Users cannot send messages to themselves");
        }

        Message message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .senderUsername(sender.getUsername())
                .recipientUsername(recipient.getUsername())
                .content(request.getContent())
                .messageSent(Instant.now())
                .build();

        Message savedMessage = messageRepository.save(message);
        return toDto(savedMessage);
    }

    @Transactional
    public List<MessageDto> getMessageThread(String currentUsername, String recipientUsername) {

        AppUser currentUser = appUserRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + currentUsername));
        AppUser recipientUser = appUserRepository.findByUsername(recipientUsername)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + recipientUsername));

        // Fetch the thread containing all messages between the users
        List<Message> messages = messageRepository.findMessageThreadBetweenUsers(currentUser.getId(), recipientUser.getId());

        // Mark messages as read only for the current user as recipient
        markUnreadMessagesAsRead(messages, currentUser);

        return messages.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markUnreadMessagesAsRead(List<Message> messages, AppUser recipient) {
        messages.stream()
                .filter(message -> message.getRecipient().equals(recipient) && message.getDateRead() == null)
                .forEach(message -> {
                    message.setDateRead(Instant.now());
                    messageRepository.save(message);
                });
    }

    public Page<MessageDto> getMessagesForUser(MessageParams params) {
        PageRequest pageRequest = PageRequest.of(params.getPage(), params.getSize());
        Page<Message> messages = messageRepository.findMessagesForUser(params.getUsername(), params.getContainer(), pageRequest);

        return messages.map(this::toDto);
    }

    @Transactional
    public void deleteMessage(Long messageId, String currentUsername) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message not found with ID: " + messageId));

        if (message.getSenderUsername().equals(currentUsername)) {
            message.setSenderDeleted(true);
        } else if (message.getRecipientUsername().equals(currentUsername)) {
            message.setRecipientDeleted(true);
        } else {
            throw new IllegalArgumentException("You are not authorized to delete this message.");
        }

        messageRepository.save(message);
    }

    private MessageDto toDto(Message message) {

        String senderPhotoUrl = message.getSender().getPhotos().stream()
                .filter(Photo::isMain)
                .findFirst()
                .map(Photo::getUrl)
                .orElse(null);

        String recipientPhotoUrl = message.getRecipient().getPhotos().stream()
                .filter(Photo::isMain)
                .findFirst()
                .map(Photo::getUrl)
                .orElse(null);

        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .senderUsername(message.getSenderUsername())
                .senderPhotoUrl(senderPhotoUrl)
                .recipientId(message.getRecipient().getId())
                .recipientUsername(message.getRecipientUsername())
                .recipientPhotoUrl(recipientPhotoUrl)
                .content(message.getContent())
                .dateRead(message.getDateRead())
                .messageSent(message.getMessageSent())
                .build();
    }
}
