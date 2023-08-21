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

import com.evalia.backend.models.Professional;
import com.evalia.backend.services.ProfessionalService;
@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @PostMapping
    public Professional createProfessional(@RequestBody Professional professional) {
        return professionalService.createProfessional(professional);
    }

    @GetMapping
    public List<Professional> getAllProfessional() {
        return professionalService.getAllProfessional();
    }

    @GetMapping("/{id}")
    public Professional getProfessionalById(@PathVariable Long id) {
        return professionalService.getProfessionalById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }
}