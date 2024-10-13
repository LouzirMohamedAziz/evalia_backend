package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Ratable;

@RepositoryRestResource(exported=false)
public interface RatableRepository
	extends CrudRepository<Ratable, String>, JpaSpecificationExecutor<Ratable>{
	
}
