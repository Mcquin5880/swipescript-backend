package com.mcq.swipescriptbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.mcq.swipescriptbackend.dto.PhotoDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Photo;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoService {

    private final Cloudinary cloudinary;
    private final AppUserRepository appUserRepository;
    private final PhotoRepository photoRepository;

    public PhotoDto uploadPhotoForAuthenticatedUser(MultipartFile file) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String photoUrl = uploadPhotoToCloudinary(file);

        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Photo photo = Photo.builder()
                .url(photoUrl)
                .isMain(user.getPhotos().isEmpty())
                .appUser(user)
                .build();

        photoRepository.save(photo);
        user.getPhotos().add(photo);
        appUserRepository.save(user);
        return toPhotoDto(photo);
    }

    private String uploadPhotoToCloudinary(MultipartFile file) throws IOException {

        Transformation transformation = new Transformation()
                .width(500)
                .height(500)
                .crop("fill")
                .gravity("auto");

        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", true,
                "overwrite", true,
                "transformation", transformation
        ));

        return (String) result.get("secure_url");
    }

    public void setMainPhoto(long photoId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));

        if (!photo.getAppUser().equals(user)) {
            throw new IllegalArgumentException("Unauthorized action");
        }

        user.getPhotos().forEach(p -> p.setMain(false));
        photo.setMain(true);

        appUserRepository.save(user);
        photoRepository.save(photo);
    }

    public void deletePhoto(long photoId) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found"));

        if (!photo.getAppUser().equals(user)) {
            throw new IllegalArgumentException("Unauthorized action");
        }

        if (photo.isMain()) {
            throw new IllegalArgumentException("Cannot delete the main photo");
        }

        // Attempt to remove the photo from Cloudinary
        try {
            Map<String, Object> result = cloudinary.uploader().destroy(photo.getUrl(), ObjectUtils.emptyMap());
            if (!"ok".equals(result.get("result"))) {
                log.warn("Photo not found on Cloudinary or deletion failed: {}", photo.getUrl());
            } else {
                log.info("Photo successfully deleted from Cloudinary: {}", photo.getUrl());
            }
        } catch (Exception e) {
            log.error("Error deleting photo from Cloudinary (possibly hosted locally): {}", e.getMessage(), e);
        }

        user.getPhotos().remove(photo);
        photoRepository.delete(photo);
    }

    private PhotoDto toPhotoDto(Photo photo) {
        return new PhotoDto(
                photo.getId(),
                photo.getUrl(),
                photo.isMain()
        );
    }
}
