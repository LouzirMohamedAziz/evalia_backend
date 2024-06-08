package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    
}
