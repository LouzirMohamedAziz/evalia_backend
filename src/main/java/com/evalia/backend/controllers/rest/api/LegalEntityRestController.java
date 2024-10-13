package com.evalia.backend.controllers.rest.api;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.controllers.impl.LegalEntityController;
import com.evalia.backend.models.LegalEntity;

@RestController
@RequestMapping("/api/legal_entities")
public class LegalEntityRestController {
	
	private LegalEntityController legalEntityController;

	
	public LegalEntityRestController(LegalEntityController legalEntityController) {
		this.legalEntityController = legalEntityController;
	}
	
	@PostMapping
	public ResponseEntity<LegalEntity> add(@RequestBody LegalEntity legalEntity) {
		
		if(Objects.isNull(legalEntity)) {
			return ResponseEntity.badRequest().build();
		}
		legalEntity = legalEntityController.add(legalEntity);
		return ResponseEntity.ok(legalEntity);
	}

}
