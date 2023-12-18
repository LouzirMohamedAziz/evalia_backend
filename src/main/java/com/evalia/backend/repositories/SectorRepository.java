package com.evalia.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.evalia.backend.models.Sector;

public interface SectorRepository extends PagingAndSortingRepository<Sector, String> {

}
