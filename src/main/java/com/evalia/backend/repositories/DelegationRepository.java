package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Delegation;

@RepositoryRestResource(exported = false)
public interface DelegationRepository extends CrudRepository<Delegation, Long> {

}
