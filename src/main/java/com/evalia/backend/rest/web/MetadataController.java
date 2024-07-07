package com.evalia.backend.rest.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.metadata.ActorType;
import com.evalia.backend.metadata.Enumeration;
import com.evalia.backend.metadata.Performance;

@RestController
@RequestMapping("/metadata")
public class MetadataController {
	

	@GetMapping("/actors/type")
	public Map<Enumeration, String> getRatableActors() {
		return Enumeration.getValues(ActorType.values());
	}

	@GetMapping("/performances")
	public Map<Enumeration, String> getPerformances() {
		return Enumeration.getValues(Performance.values());
	}

}