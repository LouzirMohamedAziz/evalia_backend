package com.evalia.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Country;
import com.evalia.backend.models.Governorate;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
	
	@RestResource(path = "govs")
	@Query("select c.governorates from Country c where c.id = :id")
	List<Governorate> getGovernorates(@Param("id") Long id);
}
