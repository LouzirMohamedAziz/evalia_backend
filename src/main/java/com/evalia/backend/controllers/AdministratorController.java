package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Administrator;
import com.evalia.backend.services.AdministratorService;
@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;
    
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    public Administrator createAdministrator(@RequestBody Administrator adminsitrator) {
        return administratorService.createAdministrator(adminsitrator);
    }

    @GetMapping
    public List<Administrator> getAllAdministrators() {
        return administratorService.getAllAdministrators();
    }

    @GetMapping("/{id}")
    public Administrator getAdministratorById(@PathVariable Long id) {
        return administratorService.getAdministratorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministratorc(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
    }
}