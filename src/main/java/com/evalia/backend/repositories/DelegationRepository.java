package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Delegation;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface DelegationRepository extends JpaRepository<Delegation, Long> {

}
