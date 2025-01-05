package com.mcq.swipescriptbackend.dto;

import com.mcq.swipescriptbackend.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AppUserDto {

    private int id;
    private String username;
    private String knownAs;
    private int age;
    private LocalDateTime created;
    private LocalDateTime lastActive;
    private String gender;
    private String introduction;
    private String interests;
    private String lookingFor;
    private String city;
    private String country;
    private List<Photo> photos;
}
