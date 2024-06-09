package com.evalia.backend.ctrl.services;

import java.util.List;
import java.util.Map;

import com.evalia.backend.models.Rating;

public interface RatingController {

	
	public List<Rating> search(Map<String, String> criterions);
	
}
