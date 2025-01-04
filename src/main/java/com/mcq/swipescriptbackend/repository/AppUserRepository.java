package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
}
