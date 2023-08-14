package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.ERole;
import com.evalia.backend.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);
}