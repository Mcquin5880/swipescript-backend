package com.mcq.swipescriptbackend.util;

import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Photo;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        List<AppUser> testUsers = List.of(
                createAppUser("bella_barker", "fluffydog456", LocalDate.of(2019, 8, 12), "Bella",
                        List.of(
                                createPhoto("https://example.com/photos/bella1.jpg", true),
                                createPhoto("https://example.com/photos/bella2.jpg", false)
                        )),
                createAppUser("cocoa_the_chill", "lazybones123", LocalDate.of(2015, 6, 5), "Cocoa",
                        List.of(
                                createPhoto("https://example.com/photos/cocoa1.jpg", true),
                                createPhoto("https://example.com/photos/cocoa2.jpg", false)
                        )),
                createAppUser("howling_henry", "awooo123", LocalDate.of(2016, 10, 31), "Henry",
                        List.of(
                                createPhoto("https://example.com/photos/henry1.jpg", true),
                                createPhoto("https://example.com/photos/henry2.jpg", false)
                        )),
                createAppUser("daisy_the_diva", "glamdog123", LocalDate.of(2021, 2, 14), "Daisy",
                        List.of(
                                createPhoto("https://example.com/photos/daisy1.jpg", true),
                                createPhoto("https://example.com/photos/daisy2.jpg", false)
                        )),
                createAppUser("wiggly_walter", "wagwag456", LocalDate.of(2019, 4, 10), "Walter",
                        List.of(
                                createPhoto("https://example.com/photos/walter1.jpg", true),
                                createPhoto("https://example.com/photos/walter2.jpg", false)
                        )),
                createAppUser("snuggles_sophie", "cozydog789", LocalDate.of(2018, 12, 25), "Sophie",
                        List.of(
                                createPhoto("https://example.com/photos/sophie1.jpg", true),
                                createPhoto("https://example.com/photos/sophie2.jpg", false)
                        ))
        );

        appUserRepository.saveAll(testUsers);
    }

    private AppUser createAppUser(String username, String password, LocalDate dateOfBirth, String knownAs, List<Photo> photos) {
        AppUser user = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .dateOfBirth(dateOfBirth)
                .knownAs(knownAs)
                .created(LocalDateTime.now().minusDays(30))
                .lastActive(LocalDateTime.now())
                .gender("Male")
                .introduction("Sample introduction")
                .interests("Sample interests")
                .lookingFor("Sample lookingFor")
                .city("Sample city")
                .country("Sample country")
                .photos(photos)
                .build();

        // Set the reverse relationship
        photos.forEach(photo -> photo.setAppUser(user));

        return user;
    }

    private Photo createPhoto(String url, boolean isMain) {
        return Photo.builder()
                .url(url)
                .isMain(isMain)
                .build();
    }
}
