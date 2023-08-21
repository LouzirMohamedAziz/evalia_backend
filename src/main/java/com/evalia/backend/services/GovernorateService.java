
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.GovernorateRepository;

@Service
public class GovernorateService {

    private final GovernorateRepository governorateRepository;

    @Autowired
    public GovernorateService(GovernorateRepository governorateRepository) {
        this.governorateRepository = governorateRepository;
    }

    public Governorate createGovernorate(Governorate governorate) {
        return governorateRepository.save(governorate);
    }

    public List<Governorate> getAllDelegations() {
        return governorateRepository.findAll();
    }

    public Governorate getDelegationById(Long id) {
        return governorateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Governorate not found with id: " + id));
    }

    public void deleteEntity(Long id) {
        governorateRepository.deleteById(id);
    }
}