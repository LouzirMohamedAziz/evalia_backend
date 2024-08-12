package com.evalia.backend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Image;

@RepositoryRestResource(exported = false)
public interface ImageRepository extends JpaRepository<Image,Long> {

}