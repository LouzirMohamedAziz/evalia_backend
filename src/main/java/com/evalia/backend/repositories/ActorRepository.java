package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Actor;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface ActorRepository extends CrudRepository<Actor, String> {
	
}
