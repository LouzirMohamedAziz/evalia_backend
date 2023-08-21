package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Delegation;
import com.evalia.backend.repositories.DelegationRepository;

@Service
public class DelegationService {

    private final DelegationRepository delegationRepository;

    @Autowired
    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    public Delegation createDelegation(Delegation delegation) {
        return delegationRepository.save(delegation);
    }

    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }

    public Delegation getDelegationById(Long id) {
        return delegationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delegation not found with id: " + id));
    }

    public void deleteDelegation(Long id) {
        delegationRepository.deleteById(id);
    }
}