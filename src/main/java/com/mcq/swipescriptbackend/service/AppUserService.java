package com.mcq.swipescriptbackend.service;

import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.dto.MemberUpdateDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Transactional
    public void updateUserProfile(AppUser user, MemberUpdateDto memberUpdateDto) {
        user.setIntroduction(memberUpdateDto.getIntroduction());
        user.setLookingFor(memberUpdateDto.getLookingFor());
        user.setInterests(memberUpdateDto.getInterests());
        user.setCity(memberUpdateDto.getCity());
        user.setCountry(memberUpdateDto.getCountry());
        user.setLastActive(LocalDateTime.now());
    }
}
