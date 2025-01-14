package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AppUserRepository appUserRepository;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        log.info("User found: {}", userOptional.isPresent());

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        appUserRepository.delete(userOptional.get());
        return ResponseEntity.noContent().build();
    }
}
