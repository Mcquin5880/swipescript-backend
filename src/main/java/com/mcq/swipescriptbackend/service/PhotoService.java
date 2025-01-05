package com.mcq.swipescriptbackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mcq.swipescriptbackend.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class PhotoService {

    private final Cloudinary cloudinary;

    @Autowired
    public PhotoService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Upload Photo and Return a PhotoDto
    public PhotoDto uploadPhoto(MultipartFile file) throws IOException {
        // Extract original filename (without extension)
        String originalFilename = file.getOriginalFilename();
        String publicId = originalFilename != null ? originalFilename.split("\\.")[0] : "default";

        // Configure upload parameters
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", true,  // Ensure unique public IDs
                "public_id", publicId,   // Optional custom public_id
                "overwrite", true
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

