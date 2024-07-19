package com.evalia.backend.web.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<Rating> add(@RequestBody Rating rating) {
		rating = ratingController.add(rating);
		return ResponseEntity.ok(rating);
	}
	
	@PutMapping
	public void edit(@RequestBody Rating rating) {
		ratingController.edit(rating);
	}
	
	@PostMapping("/{id}/attachment")
	public void attach(@PathVariable("id") Long id,
			@RequestParam("attachment") MultipartFile file) {
		ratingController.attach(id, file);
	}
	
	@GetMapping("/{id}/attachment")
	public ResponseEntity<Resource> getAttachment(@PathVariable("id") Long id) {
		Resource resource = ratingController.getAttachment(id);
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, 
						"attachment; filename=" + resource.getFilename())
				.body(resource);
	}
}
