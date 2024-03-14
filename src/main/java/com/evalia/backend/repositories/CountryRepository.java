package com.evalia.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.evalia.backend.models.Country;
import com.evalia.backend.models.Governorate;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

	
	@RestResource(path = "govs")
	@Query("select c.governorates from Country c where c.id = :id")
	List<Governorate> getGovernorates(@Param("id") Long id);


	Optional<Country> findByName(String name);
}
