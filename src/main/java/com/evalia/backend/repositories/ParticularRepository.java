package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Particular;

@Repository
public interface ParticularRepository extends  JpaRepository<Particular, Long> {

}
