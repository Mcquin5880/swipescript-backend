package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.dto.AppUserDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@Slf4j
public class LikesController {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @PostMapping("/{id}")
    public ResponseEntity<String> toggleLike(@PathVariable int id) {

        AppUser currentUser = getCurrentUser();

        if (currentUser.getId() == id) {
            return ResponseEntity.badRequest().body("You cannot like/unlike yourself.");
        }

        AppUser userToLike = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // If already liked, remove (unlike)
        if (currentUser.getLikedUsers().contains(userToLike)) {
            currentUser.getLikedUsers().remove(userToLike);
            appUserRepository.save(currentUser);
            return ResponseEntity.ok("User " + id + " unliked.");
        } else {
            // Otherwise, add to the set (like)
            currentUser.getLikedUsers().add(userToLike);
            appUserRepository.save(currentUser);
            return ResponseEntity.ok("User " + id + " liked.");
        }
    }

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getLikes(@RequestParam String predicate) {

        AppUser currentUser = getCurrentUser();

        List<AppUser> results;
        switch (predicate) {
            case "liked": // who I like
                results = new ArrayList<>(currentUser.getLikedUsers());
                break;
            case "likedBy": // who likes me
                results = new ArrayList<>(currentUser.getLikedByUsers());
                break;
            case "mutual": // intersection
                Set<AppUser> iLike = new HashSet<>(currentUser.getLikedUsers());
                Set<AppUser> likesMe = new HashSet<>(currentUser.getLikedByUsers());
                iLike.retainAll(likesMe);
                results = new ArrayList<>(iLike);
                break;
            default:
                // Return empty or a 400 if unknown predicate
                return ResponseEntity.badRequest().build();
        }

        List<AppUserDto> dtos = results.stream()
                .map(appUserService::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private AppUser getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
    }
}
