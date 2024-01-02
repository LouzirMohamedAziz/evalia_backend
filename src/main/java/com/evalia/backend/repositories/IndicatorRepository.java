package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Indicator;

public interface IndicatorRepository extends CrudRepository<Indicator,String>{
    
}