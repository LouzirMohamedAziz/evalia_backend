package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Sector;

@Repository
public interface SectorRepository extends  JpaRepository<Sector, Long> {

}
