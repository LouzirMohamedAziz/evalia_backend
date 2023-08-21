package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Entity;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {

}
