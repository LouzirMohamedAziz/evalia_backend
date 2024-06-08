package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Post;

@RepositoryRestResource(exported = false)
public interface PostRepository extends JpaRepository<Post,Long>{
    
}