
package com.evalia.backend.services;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.evalia.backend.models.Particular;
import com.evalia.backend.repositories.ParticularRepository;

@Service
public class ParticularService {

    private final ParticularRepository particularRepository;

    public ParticularService(ParticularRepository particularRepository) {
        this.particularRepository = particularRepository;
    }

    public Particular createParticular(Particular particular) {
        return particularRepository.save(particular);
    }

    public List<Particular> getAllParticular() {
        return particularRepository.findAll();
    }

    public Particular getParticularById(Long id) {
        return particularRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Particular not found with id: " + id));
    }

    public void deleteParticular(Long id) {
        particularRepository.deleteById(id);
    }
}