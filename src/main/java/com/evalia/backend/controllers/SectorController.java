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

import com.evalia.backend.models.Sector;
import com.evalia.backend.services.SectorService;
@RestController
@RequestMapping("/sectors")
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    public Sector createSector(@RequestBody Sector sectors) {
        return sectorService.createSector(sectors);
    }
    
    @GetMapping
    public List<Sector> getAllSector() {
        return sectorService.getAllSector();
    }

    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable Long id) {
        return sectorService.getSectorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
    }
}