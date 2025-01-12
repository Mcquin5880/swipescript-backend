package com.mcq.swipescriptbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private long id;

    private String senderUsername;
    private String recipientUsername;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime dateRead;
    private LocalDateTime messageSent;
    private boolean senderDeleted;
    private boolean recipientDeleted;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private AppUser sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private AppUser recipient;
}
