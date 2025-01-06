package com.mcq.swipescriptbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String username;
    private String token;
    private String photoUrl;
}
