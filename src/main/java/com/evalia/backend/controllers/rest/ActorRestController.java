package com.evalia.backend.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.evalia.backend.controllers.ActorController;
import com.evalia.backend.models.Actor;

@RestController
@RequestMapping("/api/actors")
public class ActorRestController {
        private final ActorController actorController;

    public ActorRestController(ActorController actorController) {
        this.actorController = actorController;
    }
    @PutMapping("/edit")
    public Actor editActorDescription(@RequestParam(required = true) String actorId,@RequestParam(required = true) String description){
        try {
            Actor actor = actorController.editDescription(actorId, description);
            return actor;
        } catch (Exception e) {
            System.out.println("Error when saving the image: "+e.getMessage());
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    
    }
}
