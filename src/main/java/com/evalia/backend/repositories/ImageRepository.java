package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {

}