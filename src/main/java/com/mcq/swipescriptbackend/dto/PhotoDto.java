package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoDto {
    private String url;
    private String publicId;
    private String format;
    private int bytes;
    private int width;
    private int height;
    private String createdAt;
}
