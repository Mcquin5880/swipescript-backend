package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageRequestDto {
    private String recipientUsername;
    private String content;
}
