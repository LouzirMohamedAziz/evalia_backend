package com.evalia.backend.controllers.services;

import java.util.List;

import com.evalia.backend.models.Ratable;

public interface RatableService {
	
	public List<Ratable> search(Long governorate, Long delegation,
			String sector, String subSector);
}
