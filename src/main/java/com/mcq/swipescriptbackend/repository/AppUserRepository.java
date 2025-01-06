package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
    Page<AppUser> findAll(Pageable pageable);
}
