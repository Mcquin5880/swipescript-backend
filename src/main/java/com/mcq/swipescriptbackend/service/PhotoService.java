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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final Cloudinary cloudinary;
    private final AppUserRepository appUserRepository;
    private final PhotoRepository photoRepository;

    public PhotoDto uploadPhotoForAuthenticatedUser(MultipartFile file) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PhotoDto photoDto = uploadPhoto(file);

        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Photo photo = Photo.builder()
                .url(photoDto.getUrl())
                .isMain(user.getPhotos().isEmpty()) // Set as main photo if this is the first photo
                .appUser(user)
                .build();

        photoRepository.save(photo);

        user.getPhotos().add(photo);
        appUserRepository.save(user);

        return photoDto;
    }

    public PhotoDto uploadPhoto(MultipartFile file) throws IOException {
        // Configure photo transformation
        Transformation transformation = new Transformation()
                .width(500)       // Set width to 500px
                .height(500)      // Set height to 500px
                .crop("fill")     // Crop to fill the specified dimensions
                .gravity("auto"); // Optional: Center the image automatically

        // Configure upload parameters with the transformation
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", true,
                "overwrite", true,
                "transformation", transformation
        );

        // Upload file to Cloudinary
        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        // Return a PhotoDto
        return new PhotoDto(
                (String) result.get("secure_url"),
                (String) result.get("public_id"),
                (String) result.get("format"),
                (int) result.get("bytes"),
                (int) result.get("width"),
                (int) result.get("height"),
                (String) result.get("created_at")
        );
    }

    public Map<String, Object> getPhotoDetails(String publicId) throws Exception {
        return cloudinary.api().resource(publicId, ObjectUtils.asMap("quality_analysis", true));
    }

    public Map<String, Object> deletePhoto(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
