package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.dto.PhotoDto;
import com.mcq.swipescriptbackend.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhotoForUser(@RequestParam("file") MultipartFile file) {
        try {
            PhotoDto photoDto = photoService.uploadPhotoForAuthenticatedUser(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(photoDto);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PatchMapping("/{photoId}/set-main")
    public ResponseEntity<Void> setMainPhoto(@PathVariable int photoId) {
        try {
            photoService.setMainPhoto(photoId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<Map<String, Object>> getPhotoDetails(@PathVariable String publicId) {
        try {
            Map<String, Object> result = photoService.getPhotoDetails(publicId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to fetch photo details"));
        }
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Map<String, Object>> deletePhoto(@PathVariable String publicId) {
        try {
            Map<String, Object> result = photoService.deletePhoto(publicId);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Photo deletion failed"));
        }
    }
}
