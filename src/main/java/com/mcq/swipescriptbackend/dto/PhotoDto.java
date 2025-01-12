package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoDto {
    
    private long id;
    private String url;
    private boolean isMain;
}
