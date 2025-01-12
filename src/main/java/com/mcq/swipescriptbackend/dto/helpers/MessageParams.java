package com.mcq.swipescriptbackend.dto.helpers;

import lombok.Data;

@Data
public class MessageParams {
    private String username;
    private String container = "Unread";
    private int page = 0;
    private int size = 10;
}
