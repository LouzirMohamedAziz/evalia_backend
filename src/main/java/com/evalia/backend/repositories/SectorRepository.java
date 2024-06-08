package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Sector;

public interface SectorRepository extends JpaRepository<Sector, String> {

}
