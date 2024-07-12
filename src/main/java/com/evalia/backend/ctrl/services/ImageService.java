package com.evalia.backend.ctrl.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.models.Image;

public interface ImageService {

    public void uploadAvatar(String id, MultipartFile file) throws IOException;
    
    public void uploadCover(String id, MultipartFile file) throws IOException;

    public Image getAvatar(String id);
    
    public Image getCover(String id);
}
