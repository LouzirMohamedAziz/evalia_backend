package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Address;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
	
}
