package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Address;

/**
 * @author Mohamed Ben Hamouda
 *
 */
@RepositoryRestResource
public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findById(Long id);
}
