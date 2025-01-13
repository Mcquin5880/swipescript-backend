package com.mcq.swipescriptbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcq.swipescriptbackend.dto.CreateMessageRequestDto;
import com.mcq.swipescriptbackend.dto.MessageDto;
import com.mcq.swipescriptbackend.dto.helpers.PaginationMetadata;
import com.mcq.swipescriptbackend.service.MessageService;
import com.mcq.swipescriptbackend.dto.helpers.MessageParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@RequestBody CreateMessageRequestDto request) {
        String currentUsername = getCurrentUsername();
        MessageDto sentMessage = messageService.sendMessage(request, currentUsername);
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping
    public ResponseEntity<Page<MessageDto>> getMessages(MessageParams params) throws JsonProcessingException {
        params.setUsername(getCurrentUsername());
        Page<MessageDto> messages = messageService.getMessagesForUser(params);

        ObjectMapper objectMapper = new ObjectMapper();
        String paginationJson = objectMapper.writeValueAsString(new PaginationMetadata(
                messages.getNumber() + 1,
                messages.getSize(),
                messages.getTotalElements(),
                messages.getTotalPages()
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Pagination", paginationJson);

        return ResponseEntity.ok().headers(headers).body(messages);
    }

    @GetMapping("/thread")
    public ResponseEntity<List<MessageDto>> getMessageThread(@RequestParam String recipientUsername) {
        String currentUsername = getCurrentUsername();
        List<MessageDto> thread = messageService.getMessageThread(currentUsername, recipientUsername);
        return ResponseEntity.ok(thread);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        String currentUsername = getCurrentUsername();
        messageService.deleteMessage(messageId, currentUsername);
        return ResponseEntity.noContent().build();
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
