package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Governorate;

@Repository
public interface GovernorateRepository extends CrudRepository<Governorate, Long> {

}
