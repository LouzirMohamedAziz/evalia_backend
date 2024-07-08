package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Authority;

/**
 * @author Hamdi Jandoubi
 *
 */
@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
}
