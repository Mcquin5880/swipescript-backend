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

        Transformation transformation = new Transformation()
                .width(500)
                .height(500)
                .crop("fill")
                .gravity("auto");

        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", true,
                "overwrite", true,
                "transformation", transformation
        );

        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), uploadParams);

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

    public void setMainPhoto(int photoId) {

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

    public Map<String, Object> getPhotoDetails(String publicId) throws Exception {
        return cloudinary.api().resource(publicId, ObjectUtils.asMap("quality_analysis", true));
    }

    public Map<String, Object> deletePhoto(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
