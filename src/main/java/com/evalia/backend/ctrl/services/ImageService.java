package com.evalia.backend.ctrl.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.models.Image;

public interface ImageService {

    default Image getImageDetails(String imageName) {
        throw new UnsupportedOperationException();
    }

    public void uplaodImage(String id, MultipartFile file);

    public ResponseEntity<byte[]> getImage(String id);
}
