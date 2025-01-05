package com.mcq.swipescriptbackend;

import com.mcq.swipescriptbackend.config.CloudinaryConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwipescriptBackendApplication {

    // todo cleanup

//    @Autowired
//    private CloudinaryConfig cloudinaryConfig;

    public static void main(String[] args) {
        SpringApplication.run(SwipescriptBackendApplication.class, args);
    }

//    @PostConstruct
//    public void printCloudinaryConfig() {
//        System.out.println("Cloudinary Config:");
//        System.out.println("Cloud Name: " + cloudinaryConfig.cloudinary().config.cloudName);
//        System.out.println("API Key: " + cloudinaryConfig.cloudinary().config.apiKey);
//        System.out.println("API Secret: " + cloudinaryConfig.cloudinary().config.apiSecret);
//    }
}
