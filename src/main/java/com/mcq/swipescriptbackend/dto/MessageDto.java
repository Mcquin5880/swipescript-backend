package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private long id;
    private long senderId;
    private String senderUsername;
    private String senderPhotoUrl;
    private long recipientId;
    private String recipientUsername;
    private String recipientPhotoUrl;
    private String content;
    private Instant dateRead;
    private Instant messageSent;
}
