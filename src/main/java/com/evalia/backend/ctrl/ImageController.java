package com.evalia.backend.ctrl;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.ctrl.services.ImageService;
import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Actor;
import com.evalia.backend.models.Image;
import com.evalia.backend.repositories.ActorRepository;

@Controller
public class ImageController implements ImageService{

    
    private ActorRepository actorRepository;

    public ImageController(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }
    
    private static Image buildImage(MultipartFile file) throws IOException {
    	Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setContent(file.getBytes());
        return image;
    }

    @Override
    public void uploadAvatar(String id, MultipartFile file) throws IOException{
    	
        Actor actor = actorRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));
        actor.setAvatar(buildImage(file));
        actorRepository.save(actor);
    }

    public Image getAvatar(String id){
        Actor actor = actorRepository.findById(id)
            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));

        Image image = actor.getAvatar();
        return image;
    }
    

	@Override
	public void uploadCover(String id, MultipartFile file) throws IOException{
		Actor actor = actorRepository.findById(id)
	            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));
		actor.setCover(buildImage(file));
		actorRepository.save(actor);
	}

	@Override
	public Image getCover(String id) {
		Actor actor = actorRepository.findById(id)
	            .orElseThrow(() -> ResourceNotFoundException.build(Actor.class.getName(), id));

	        Image image = actor.getCover();
	        return image;
	}
}
