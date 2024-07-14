package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.evalia.backend.models.Professional;

@RestResource(exported = false)
public interface ProfessionalRepository 
	extends CrudRepository<Professional, String>, JpaSpecificationExecutor<Professional>{
	
}
