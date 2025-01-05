package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        List<AppUserDto> userDtos = appUserRepository.findAll()
                .stream()
                .map(appUserService::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserDto> getUserByUsername(@PathVariable String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(appUserService.convertToDto(user));
    }
}
