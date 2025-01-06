package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT u FROM AppUser u " +
            "WHERE (:gender IS NULL OR u.gender = :gender) " +
            "AND (u.username <> :currentUsername)")
    Page<AppUser> findFilteredUsers(
            @Param("gender") String gender,
            @Param("currentUsername") String currentUsername,
            Pageable pageable
    );
}
