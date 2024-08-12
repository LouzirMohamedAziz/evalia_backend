package com.evalia.backend.controllers;

import org.springframework.stereotype.Controller;

import com.evalia.backend.models.Actor;
import com.evalia.backend.repositories.ActorRepository;

@Controller
public class ActorController {
        private ActorRepository actorRepository;

        // This is Actor Controller
    public ActorController(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public Actor editDescription(String actorId, String Description) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        if (actor != null) {
            actor.setDescription(Description);
            actorRepository.save(actor);
        }
        return actor;
    }
    
}
