package com.evalia.backend.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.evalia.backend.models.Image;


@RestResource(exported = false)
public interface ImageRepository extends JpaRepository<Image,Long> {

}