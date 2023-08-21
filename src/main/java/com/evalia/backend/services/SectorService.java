
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Sector;
import com.evalia.backend.repositories.SectorRepository;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector createSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    public List<Sector> getAllSector() {
        return sectorRepository.findAll();
    }

    public Sector getSectorById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sector not found with id: " + id));
    }

    public void deleteSector(Long id) {
        sectorRepository.deleteById(id);
    }
}