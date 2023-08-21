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

import com.evalia.backend.models.Entity;
import com.evalia.backend.services.EntityService;
@RestController
@RequestMapping("/entities")
public class EntityController {

    private final EntityService entityService;

    @Autowired
    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping
    public Entity createEntity(@RequestBody Entity entity) {
        return entityService.createEntity(entity);
    }

    @GetMapping
    public List<Entity> getAllEntity() {
        return entityService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Entity getEntitynById(@PathVariable Long id) {
        return entityService.getEntityById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntityc(@PathVariable Long id) {
        entityService.deleteEntity(id);
    }
}