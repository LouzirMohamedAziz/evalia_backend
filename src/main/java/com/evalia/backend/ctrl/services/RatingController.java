package com.evalia.backend.ctrl.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.models.Rating;

public interface RatingController {

	public List<Rating> search(Map<String, String> criterions);
	
	public Rating add(Rating rating);
	
	public void edit(Rating rating);
	
	public void delete(Long id);
	
	public void attach(Long id, MultipartFile file);
	
	public Resource getAttachment(Long id);
	
}
