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
    private long id;

    private String username;

    private String password;

    private LocalDate dateOfBirth;

    private String knownAs;

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime lastActive;

    private String gender;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String interests;

    @Column(columnDefinition = "TEXT")
    private String lookingFor;

    private String city;

    private String state;

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

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messagesReceived;

    public int getAge() {
        if (dateOfBirth == null) {
            throw new IllegalStateException("Date of birth is not set.");
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();
}
