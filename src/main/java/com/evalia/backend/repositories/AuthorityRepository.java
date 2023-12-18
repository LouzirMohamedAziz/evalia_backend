package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Authority;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
}
