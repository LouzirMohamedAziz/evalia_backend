
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Entity;
import com.evalia.backend.repositories.EntityRepository;

@Service
public class EntityService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Entity createEntity(Entity entity) {
        return entityRepository.save(entity);
    }

    public List<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    public Entity getEntityById(Long id) {
        return entityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public void deleteEntity(Long id) {
        entityRepository.deleteById(id);
    }
}