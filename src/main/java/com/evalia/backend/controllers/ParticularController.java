package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Particular;
import com.evalia.backend.services.ParticularService;
@RestController
@RequestMapping("/particulars")
public class ParticularController {

    private final ParticularService particularService;

    public ParticularController(ParticularService particularService) {
        this.particularService = particularService;
    }

    @PostMapping
    public Particular createParticular(@RequestBody Particular professional) {
        return particularService.createParticular(professional);
    }

    @GetMapping
    public List<Particular> getAllParticular() {
        return particularService.getAllParticular();
    }

    @GetMapping("/{id}")
    public Particular getIndicatornById(@PathVariable Long id) {
        return particularService.getParticularById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteParticularc(@PathVariable Long id) {
        particularService.deleteParticular(id);
    }
}