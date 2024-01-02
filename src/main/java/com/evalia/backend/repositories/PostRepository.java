package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Post;

public interface PostRepository extends CrudRepository<Post,Long>{
    
}