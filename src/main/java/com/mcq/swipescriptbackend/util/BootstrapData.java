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
                AppUser.builder()
                        .username("Tom")
                        .password(passwordEncoder.encode("password"))
                        .build(),
                AppUser.builder()
                        .username("Bob")
                        .password(passwordEncoder.encode("password"))
                        .build(),
                AppUser.builder()
                        .username("Jane")
                        .password(passwordEncoder.encode("password"))
                        .build(),
                AppUser.builder()
                        .username("Michael")
                        .password(passwordEncoder.encode("password"))
                        .build(),
                AppUser.builder()
                        .username("Misty")
                        .password(passwordEncoder.encode("password"))
                        .build()
        );

        appUserRepository.saveAll(testUsers);
    }
}
