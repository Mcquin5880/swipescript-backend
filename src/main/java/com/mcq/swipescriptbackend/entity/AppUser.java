package com.mcq.swipescriptbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;

    private LocalDate dateOfBirth;
    private String knownAs;
    private LocalDateTime created;
    private LocalDateTime lastActive;
    private String gender;
    private String introduction;
    private String interests;
    private String lookingFor;
    private String city;
    private String country;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    public int getAge() {
        if (dateOfBirth == null) {
            throw new IllegalStateException("Date of birth is not set.");
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
