package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Entity;

@Repository
public interface EntityRepository extends CrudRepository<Entity, Long> {

	Optional<Entity> findByAccount_Login(String login);
}
