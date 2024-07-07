package com.evalia.backend.rest.web;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.ctrl.ImageController;
import com.evalia.backend.models.Image;

@RestController
@RequestMapping("/api/images")
public class ImageRestController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ImageController.class);
    private ImageController imageController;

    private ImageRestController (ImageController imageController){
        this.imageController = imageController;
    }

    @PutMapping("/actors/picture")
    public ResponseEntity<Void> uplaodImage(@RequestParam(name = "id") String id, @RequestParam("image") MultipartFile file){
        try {
            imageController.uplaodImage(id, file);
            return ResponseEntity.noContent().build();    
        } catch (IOException e) {
            LOG.error("Error when saving the image", e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @GetMapping(path = "/actors/picture")
    public ResponseEntity<byte[]> getImage(String id) {
        Image image = imageController.getImage(id);
        if(Objects.isNull(image)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(image.getContent());
        
        
    }
    
}
