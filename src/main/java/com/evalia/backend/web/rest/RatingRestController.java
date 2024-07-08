package com.evalia.backend.web.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.util.MultiValueMap;
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
	
	
	public static Map<String, String> multiToSingleValuedMap(MultiValueMap<String, String> map){
		if(Objects.isNull(map)) {
			return Collections.emptyMap();
		}
		return map.entrySet().stream()
				.collect(Collectors.groupingBy(Entry::getKey,
						Collectors.flatMapping(entry -> entry.getValue().stream(), 
								Collectors.joining())));
	}
	
	
	@GetMapping
	public List<Rating> search(@RequestBody(required = false) MultiValueMap<String, String> parameters){
		Map<String, String> params = multiToSingleValuedMap(parameters);
		return ratingController.search(params);
	}
	
	
	@PostMapping
	public void add(@RequestBody Rating rating) {
		ratingController.add(rating);
	}
}
