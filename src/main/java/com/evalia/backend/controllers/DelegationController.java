package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Delegation;
import com.evalia.backend.services.DelegationService;
@RestController
@RequestMapping("/delegations")
public class DelegationController {

    private final DelegationService delegationService;

    @Autowired
    public DelegationController(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    @PostMapping
    public Delegation createDelegation(@RequestBody Delegation delegation) {
        return delegationService.createDelegation(delegation);
    }

    @GetMapping
    public List<Delegation> getAllDelegation() {
        return delegationService.getAllDelegations();
    }

    @GetMapping("/{id}")
    public Delegation getDelegationById(@PathVariable Long id) {
        return delegationService.getDelegationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDelegationc(@PathVariable Long id) {
        delegationService.deleteDelegation(id);
    }
}