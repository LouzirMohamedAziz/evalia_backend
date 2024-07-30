package com.evalia.backend.web.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
import com.evalia.backend.util.Constants;

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

		if(map.containsKey("avg")){
			map.remove("avg");
		}

		return map.entrySet().stream()
				.collect(Collectors.groupingBy(Entry::getKey,
						Collectors.flatMapping(entry -> entry.getValue().stream(), 
								Collectors.joining())));
	}
	
	@PostMapping("/search")
	public ResponseEntity<Object> search(@RequestParam(name = "size", defaultValue = "3") int size,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "key", defaultValue = "date") String key,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "avg", defaultValue = "false") boolean avg,
			@RequestBody(required = false) MultiValueMap<String, String> parameters){
		
		
		Map<String, String> params = multiToSingleValuedMap(parameters);
		if(avg) {
			Optional<Double> ratingAvg = Optional.ofNullable(ratingController.avg(params));
			return ResponseEntity.ok(Map.entry(Constants.AVG_FIELD, ratingAvg.orElse(0D)));
		}
		Pageable pageable = PageRequest.of(page, size);
		Order order = (Direction.DESC.equals(direction) ? Order.desc(key) : Order.asc(key));
		List<Rating> ratings = ratingController.search(pageable, order, params);
		return ResponseEntity.ok(ratings);
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