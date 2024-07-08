package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Address;

/**
 * @author Mohamed Ben Hamouda
 *
 */
@RepositoryRestResource(exported = false)
public interface AddressRepository extends CrudRepository<Address, Long> {
	
}
