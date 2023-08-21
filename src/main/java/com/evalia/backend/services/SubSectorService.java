
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.SubSector;
import com.evalia.backend.repositories.SubSectorRepository;

@Service
public class SubSectorService {

    private final SubSectorRepository subSectorRepository;

    @Autowired
    public SubSectorService(SubSectorRepository subSectorRepository) {
        this.subSectorRepository = subSectorRepository;
    }

    public SubSector createSubSector(SubSector subsector) {
        return subSectorRepository.save(subsector);
    }

    public List<SubSector> getAllSubSector() {
        return subSectorRepository.findAll();
    }

    public SubSector getSubSectorById(Long id) {
        return subSectorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubSector not found with id: " + id));
    }

    public void deleteSubSector(Long id) {
        subSectorRepository.deleteById(id);
    }
}