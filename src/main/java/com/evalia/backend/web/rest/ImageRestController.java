package com.evalia.backend.web.rest;

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
    
    private static final Logger LOG = LoggerFactory.getLogger(ImageRestController.class);
    
    
    private ImageController imageController;

    
    private ImageRestController (ImageController imageController){
        this.imageController = imageController;
    }
    

    @PutMapping("/avatar")
    public ResponseEntity<Void> uploadAvatar(@RequestParam(name = "id") String id,
    		@RequestParam("avatar") MultipartFile file){
        try {
            imageController.uploadAvatar(id, file);
            return ResponseEntity.noContent().build();    
        } catch (IOException e) {
            LOG.error("Error when saving the image", e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/avatar")
    public ResponseEntity<byte[]> getAvatar(@RequestParam("id") String id) {
        Image image = imageController.getAvatar(id);
        if(Objects.isNull(image)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
        		.contentType(MediaType.valueOf(image.getType()))
        		.body(image.getContent());
    }
    
    
    @PutMapping("/cover")
    public ResponseEntity<Void> uploadCover(@RequestParam(name = "id") String id,
    		@RequestParam("cover") MultipartFile file){
        try {
            imageController.uploadCover(id, file);
            return ResponseEntity.noContent().build();    
        } catch (IOException e) {
            LOG.error("Error when saving the image", e);
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/cover")
    public ResponseEntity<byte[]> getCover(@RequestParam("id") String id) {
        Image image = imageController.getCover(id);
        if(Objects.isNull(image)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
        		.contentType(MediaType.valueOf(image.getType()))
        		.body(image.getContent());
    }
}
