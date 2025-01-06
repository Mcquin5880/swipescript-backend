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

    // todo maybe refactor url so id is at end
    @PatchMapping("/{id}/set-main")
    public ResponseEntity<Void> setMainPhoto(@PathVariable int id) {
        try {
            photoService.setMainPhoto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable int id) {
        try {
            photoService.deletePhoto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error for Cloudinary issues
        }
    }
}
