package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Country;

@RepositoryRestResource
public interface CountryRepository extends CrudRepository<Country, String> {

	Optional<Country> findByName(String name);
}
