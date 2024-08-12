package com.evalia.backend.controllers.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.multipart.MultipartFile;

import com.evalia.backend.models.Rating;

public interface RatingController {

	public Double avg(Map<String, String> criterions);
	
	public List<Rating> search(Pageable pageable, Order order, Map<String, String> criterions);
	
	public Rating add(Rating rating);
	
	public void edit(Rating rating);
	
	public void delete(Long id);
	
	public void attach(Long id, MultipartFile file);
	
	public Resource getAttachment(Long id);
	
}
