package com.evalia.backend.controllers.services;

import java.util.List;

import com.evalia.backend.models.Professional;

public interface ProfessionalService {
	
	public List<Professional> search(Long governorate, Long delegation,
			String sector, String subSector);
}
