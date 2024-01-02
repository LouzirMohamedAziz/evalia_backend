package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long>{

    
}