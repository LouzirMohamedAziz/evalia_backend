package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Rating;

@RepositoryRestResource(exported = false)
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
