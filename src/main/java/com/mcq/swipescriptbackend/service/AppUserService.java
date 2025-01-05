package com.mcq.swipescriptbackend.service;

import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    public AppUserDto convertToDto(AppUser user) {
        return new AppUserDto(
                user.getId(),
                user.getUsername(),
                user.getKnownAs(),
                user.getAge(),
                user.getCreated(),
                user.getLastActive(),
                user.getGender(),
                user.getIntroduction(),
                user.getInterests(),
                user.getLookingFor(),
                user.getCity(),
                user.getCountry(),
                user.getPhotos()
        );
    }
}
