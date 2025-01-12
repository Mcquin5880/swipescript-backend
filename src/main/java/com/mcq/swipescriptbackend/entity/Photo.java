package com.mcq.swipescriptbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;
    private boolean isMain;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Prevent recursion
    private AppUser appUser;
}
