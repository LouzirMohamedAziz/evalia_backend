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

import com.evalia.backend.models.SubSector;
import com.evalia.backend.services.SubSectorService;
@RestController
@RequestMapping("/subSectors")
public class SubSectorController {

    private final SubSectorService subSectorService;

    @Autowired
    public SubSectorController(SubSectorService subSectorService) {
        this.subSectorService = subSectorService;
    }

    @PostMapping
    public SubSector createSubSector(@RequestBody SubSector subSectors) {
        return subSectorService.createSubSector(subSectors);
    }
    
    @GetMapping
    public List<SubSector> getAllSector() {
        return subSectorService.getAllSubSector();
    }

    @GetMapping("/{id}")
    public SubSector getSectorById(@PathVariable Long id) {
        return subSectorService.getSubSectorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSector(@PathVariable Long id) {
        subSectorService.deleteSubSector(id);
    }
}