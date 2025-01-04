package com.mcq.swipescriptbackend.util;

import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        List<AppUser> testUsers = List.of(
                AppUser.builder().username("testuser").password(passwordEncoder.encode("password")).build(),
                AppUser.builder().username("Tom").build(),
                AppUser.builder().username("Bob").build(),
                AppUser.builder().username("Jane").build(),
                AppUser.builder().username("Michael").build(),
                AppUser.builder().username("Misty").build()
        );

        appUserRepository.saveAll(testUsers);
    }
}
