package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.evalia.backend.models.Country;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

	Optional<Country> findByName(String name);
}
