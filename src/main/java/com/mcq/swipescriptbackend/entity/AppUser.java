package com.mcq.swipescriptbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter  // instead of @Data, was hitting recursion issues
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
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

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "liker_id"),
            inverseJoinColumns = @JoinColumn(name = "likee_id")
    )
    private Set<AppUser> likedUsers = new HashSet<>();

    @ManyToMany(mappedBy = "likedUsers")
    private Set<AppUser> likedByUsers = new HashSet<>();

    public int getAge() {
        if (dateOfBirth == null) {
            throw new IllegalStateException("Date of birth is not set.");
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
