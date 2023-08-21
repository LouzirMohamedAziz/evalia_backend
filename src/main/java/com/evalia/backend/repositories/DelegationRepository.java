package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Delegation;

@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {

}
