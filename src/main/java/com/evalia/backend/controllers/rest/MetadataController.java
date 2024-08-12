package com.evalia.backend.controllers.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.utils.metadata.ActorType;
import com.evalia.backend.utils.metadata.Enumeration;
import com.evalia.backend.utils.metadata.Performance;

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