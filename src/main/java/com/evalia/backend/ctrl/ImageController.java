package com.evalia.backend.ctrl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Actor;
import com.evalia.backend.models.Image;
import com.evalia.backend.repositories.ActorRepository;

@RestController
@RequestMapping("/api")
public class ImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    
    private ActorRepository actorRepository;

    public ImageController(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public void uplaodImage(String id,MultipartFile file) throws IOException{
        Actor actor = actorRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setContent(file.getBytes());
            
            actor.setImage(image);
            actorRepository.save(actor);
        
    }

    public Image getImage(String id){
        Actor actor = actorRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));

        Image image = actor.getImage();
        return image;
    }

    
    
}
