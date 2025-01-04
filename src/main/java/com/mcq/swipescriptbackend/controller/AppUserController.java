package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable int id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
