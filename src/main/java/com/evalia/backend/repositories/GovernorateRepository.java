package com.evalia.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.evalia.backend.models.Governorate;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface GovernorateRepository extends PagingAndSortingRepository<Governorate, Long> {

}
