package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m " +
            "WHERE (m.sender.id = :userId1 AND m.recipient.id = :userId2) " +
            "   OR (m.sender.id = :userId2 AND m.recipient.id = :userId1) " +
            "ORDER BY m.messageSent ASC")
    List<Message> findMessageThreadBetweenUsers(Long userId1, Long userId2);

    @Query("SELECT m FROM Message m WHERE " +
            "(m.recipientUsername = :username AND m.recipientDeleted = false AND " +
            "(:container = 'Unread' AND m.dateRead IS NULL OR :container = 'Inbox')) " +
            "OR (m.senderUsername = :username AND m.senderDeleted = false AND :container = 'Outbox') " +
            "ORDER BY m.messageSent DESC")
    Page<Message> findMessagesForUser(String username, String container, Pageable pageable);

}
