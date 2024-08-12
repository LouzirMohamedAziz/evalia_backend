package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Professional;

@RepositoryRestResource(exported=false)
public interface ProfessionalRepository
	extends CrudRepository<Professional, String>, JpaSpecificationExecutor<Professional>{
	
}
