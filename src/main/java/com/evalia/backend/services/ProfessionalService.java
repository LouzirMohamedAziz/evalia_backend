
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Professional;
import com.evalia.backend.repositories.ProfessionalRepository;

@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    @Autowired
    public ProfessionalService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public Professional createProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public List<Professional> getAllProfessional() {
        return professionalRepository.findAll();
    }

    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with id: " + id));
    }

    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }
}