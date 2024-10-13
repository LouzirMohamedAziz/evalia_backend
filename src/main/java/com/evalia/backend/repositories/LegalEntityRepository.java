package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.evalia.backend.models.LegalEntity;

@RestResource(exported = false)
public interface LegalEntityRepository extends CrudRepository<LegalEntity, String> {

}
