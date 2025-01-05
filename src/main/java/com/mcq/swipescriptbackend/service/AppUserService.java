package com.mcq.swipescriptbackend.service;

import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    public AppUserDto convertToDto(AppUser appUser) {
        return new AppUserDto(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getKnownAs(),
                appUser.getAge(),
                appUser.getCreated(),
                appUser.getLastActive(),
                appUser.getGender(),
                appUser.getIntroduction(),
                appUser.getInterests(),
                appUser.getLookingFor(),
                appUser.getCity(),
                appUser.getCountry(),
                appUser.getPhotos() != null
                        ? appUser.getPhotos().stream().map(Photo::getUrl).collect(Collectors.toList())
                        : List.of() // Ensure no nulls
        );
    }
}
