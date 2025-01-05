package com.mcq.swipescriptbackend.dto;

import lombok.Data;

@Data
public class MemberUpdateDto {

    private String introduction;
    private String lookingFor;
    private String interests;
    private String city;
    private String country;
}
