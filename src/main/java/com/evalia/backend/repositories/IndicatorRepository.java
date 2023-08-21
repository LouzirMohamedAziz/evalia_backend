package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Indicator;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

}
