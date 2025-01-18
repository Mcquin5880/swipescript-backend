package com.mcq.swipescriptbackend.dto;

import com.mcq.swipescriptbackend.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class AppUserDto {

    private long id;
    private String username;
    private String photoUrl;
    private String knownAs;
    private int age;
    private Instant created;
    private Instant lastActive;
    private String gender;
    private String introduction;
    private String interests;
    private String lookingFor;
    private String city;
    private String state;
    private List<Photo> photos;
}
