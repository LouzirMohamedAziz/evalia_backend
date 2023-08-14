package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Administrative;

@Repository
public interface AdministrativeRepository extends CrudRepository<Administrative, Long> {
	
	Optional<Administrative> findByAccount_Login(String login);
}
