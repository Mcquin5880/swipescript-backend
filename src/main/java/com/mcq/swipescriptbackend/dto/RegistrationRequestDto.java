package com.mcq.swipescriptbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequestDto {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters")
    private String password;

    @NotBlank(message = "Known as is required")
    @Size(max = 20, message = "Known as must not exceed 20 characters")
    private String knownAs;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "male|female", message = "Gender must be 'male' or 'female'")
    private String gender;

    @NotBlank(message = "City is required")
    @Size(max = 20, message = "City must not exceed 20 characters")
    private String city;

    @NotBlank(message = "Country is required")
    @Size(max = 20, message = "Country must not exceed 20 characters")
    private String country;
}
