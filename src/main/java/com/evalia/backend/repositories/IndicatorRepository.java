package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Indicator;

public interface IndicatorRepository extends JpaRepository<Indicator, String>{
    
}