package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT u FROM AppUser u " +
            "WHERE (:gender IS NULL OR u.gender = :gender) " +
            "AND (:minAge IS NULL OR (YEAR(CURRENT_DATE) - YEAR(u.dateOfBirth)) >= :minAge) " +
            "AND (:maxAge IS NULL OR (YEAR(CURRENT_DATE) - YEAR(u.dateOfBirth)) <= :maxAge) " +
            "AND u.username <> :currentUsername")
    Page<AppUser> findFilteredUsers(
            @Param("gender") String gender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("currentUsername") String currentUsername,
            Pageable pageable
    );
}
