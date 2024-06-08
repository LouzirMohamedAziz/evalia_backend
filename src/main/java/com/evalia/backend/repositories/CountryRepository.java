package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Country;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface CountryRepository extends JpaRepository<Country, String> {

	Optional<Country> findByName(String name);
}
