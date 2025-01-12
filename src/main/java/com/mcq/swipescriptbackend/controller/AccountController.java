package com.mcq.swipescriptbackend.controller;

import com.mcq.swipescriptbackend.dto.LoginRequestDto;
import com.mcq.swipescriptbackend.dto.LoginResponseDto;
import com.mcq.swipescriptbackend.dto.RegistrationRequestDto;
import com.mcq.swipescriptbackend.entity.AppUser;
import com.mcq.swipescriptbackend.entity.Photo;
import com.mcq.swipescriptbackend.repository.AppUserRepository;
import com.mcq.swipescriptbackend.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {

        if (appUserRepository.findByUsername(registrationRequestDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already taken.");
        }

        AppUser newUser = AppUser.builder()
                .username(registrationRequestDto.getUsername())
                .password(passwordEncoder.encode(registrationRequestDto.getPassword()))
                .knownAs(registrationRequestDto.getKnownAs())
                .dateOfBirth(registrationRequestDto.getDateOfBirth())
                .gender(registrationRequestDto.getGender())
                .city(registrationRequestDto.getCity())
                .state(registrationRequestDto.getState())
                .build();

        appUserRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registrationRequestDto.getUsername(),
                        registrationRequestDto.getPassword()
                )
        );

        String token = jwtUtil.generateJwtToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(newUser.getUsername(), token, newUser.getGender(), newUser.getKnownAs(), null));
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );

            String jwt = jwtUtil.generateJwtToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

            AppUser user = appUserRepository.findByUsername(loginRequestDto.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            String mainPhotoUrl = user.getPhotos().stream()
                    .filter(Photo::isMain)
                    .map(Photo::getUrl)
                    .findFirst()
                    .orElse(null);

            return ResponseEntity.ok(new LoginResponseDto(user.getUsername(), jwt, user.getGender(), user.getKnownAs(), mainPhotoUrl));

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
}
