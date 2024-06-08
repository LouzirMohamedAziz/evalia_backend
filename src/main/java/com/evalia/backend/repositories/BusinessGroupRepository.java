package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.BusinessGroup;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface BusinessGroupRepository extends JpaRepository<BusinessGroup, Long> {

}
