package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Moderator;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {

}
