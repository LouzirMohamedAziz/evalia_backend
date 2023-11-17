
package com.evalia.backend.services;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.evalia.backend.models.Rating;
import com.evalia.backend.repositories.RatingRepository;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id: " + id));
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}