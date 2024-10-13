package com.evalia.backend.controllers.services;

import com.evalia.backend.models.LegalEntity;

public interface LegalEntityService {
	
	LegalEntity add(LegalEntity legalEntity);
	
	LegalEntity edit(LegalEntity legalEntity);
		
	void remove(String identifier);
}
