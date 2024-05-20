package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
}
