package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.dto.PhotoDto;
import com.mcq.swipescriptbackend.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            PhotoDto photoDto = photoService.uploadPhoto(file);
            return ResponseEntity.ok(photoDto);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
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
