package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Governorate;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface GovernorateRepository extends JpaRepository<Governorate, Long> {

}
