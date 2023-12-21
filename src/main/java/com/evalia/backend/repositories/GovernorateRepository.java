package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Governorate;

@RepositoryRestResource(exported = false)
public interface GovernorateRepository extends CrudRepository<Governorate, Long> {

}
