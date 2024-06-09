package com.evalia.backend.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.ctrl.services.RatingController;
import com.evalia.backend.models.Rating;

@RestController
@RequestMapping("/api/ratings")
public class RatingRestController {
	
	private RatingController ratingController;
	
	
	public RatingRestController(RatingController ratingController) {
		this.ratingController = ratingController;
	}
	
	
	@GetMapping
	public List<Rating> search(@RequestBody Map<String, String> criterions){
		return ratingController.search(criterions);
	}
	
	@PostMapping
	public void add(Rating rating) {
		ratingController.add(rating);
	}
}
