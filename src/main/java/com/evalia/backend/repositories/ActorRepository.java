package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Actor;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface ActorRepository extends CrudRepository<Actor, String> {
	
    //TODO: getActorNameByType
	
	public Optional<Actor> findByAccount_Username(String username);

}
