package com.evalia.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.evalia.backend.models.Delegation;

/**
 * @author Mohamed Ben Hamouda
 *
 */
public interface DelegationRepository extends PagingAndSortingRepository<Delegation, Long> {

}
