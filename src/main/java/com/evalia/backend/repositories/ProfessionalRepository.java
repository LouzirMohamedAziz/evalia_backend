package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Professional;

@Repository 
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

}
