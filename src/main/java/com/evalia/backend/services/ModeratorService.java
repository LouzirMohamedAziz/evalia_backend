
package com.evalia.backend.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Moderator;
import com.evalia.backend.repositories.ModeratorRepository;

@Service
public class ModeratorService {

    private final ModeratorRepository moderatorRepository;

    @Autowired
    public ModeratorService(ModeratorRepository moderatorRepository) {
        this.moderatorRepository = moderatorRepository;
    }

    public Moderator createModerator(Moderator moderator) {
        return moderatorRepository.save(moderator);
    }

    public List<Moderator> getAllModerator() {
        return moderatorRepository.findAll();
    }

    public Moderator getModeratorById(Long id) {
        return moderatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moderator not found with id: " + id));
    }

    public void deleteModerator(Long id) {
        moderatorRepository.deleteById(id);
    }
}