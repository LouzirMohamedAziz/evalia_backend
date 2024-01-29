package com.evalia.backend.rest.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.controllers.MetadataController;
import com.evalia.backend.metadata.Enumeration;

@RestController
@RequestMapping(path = "/metadata")
public class MetadataRestController {
	
	private MetadataController controller;
	
	
	@Autowired
	public MetadataRestController(MetadataController controller) {
		this.controller = controller;
	}
	
	
	@GetMapping(path = "/actors/ratable", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Enumeration, String> ratableActors() {
	    return controller.getRatableActors();
	}
	
	
	@GetMapping(path = "/performances", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Enumeration, String> performances() {
	    return controller.getPerformances();
	}
	
}
