package com.mcq.swipescriptbackend.repository;

import com.mcq.swipescriptbackend.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
