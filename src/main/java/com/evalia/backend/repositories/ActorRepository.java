package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Actor;

/**
 * @author Hamdi Jandoubi
 *
 */

@RepositoryRestResource(exported = false)
public interface ActorRepository extends CrudRepository<Actor, String> {
	
}
