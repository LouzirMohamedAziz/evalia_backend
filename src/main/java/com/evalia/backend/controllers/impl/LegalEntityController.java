package com.evalia.backend.controllers.impl;

import java.util.Objects;

import org.springframework.stereotype.Controller;

import com.evalia.backend.controllers.services.LegalEntityService;
import com.evalia.backend.models.Address;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.LegalEntity;
import com.evalia.backend.repositories.LegalEntityRepository;

@Controller
public class LegalEntityController implements LegalEntityService {
	
	private LegalEntityRepository legalEntityRepository;
	

	public LegalEntityController(LegalEntityRepository legalEntityRepository) {
		this.legalEntityRepository = legalEntityRepository;
	}

	@Override
	public LegalEntity add(LegalEntity legalEntity) {
		
		if(Objects.isNull(legalEntity.getName()) || legalEntity.getName().isBlank()) {
			throw new IllegalArgumentException();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(legalEntity.getName().toLowerCase().replace(" ", ""));
		
		Address addr = legalEntity.getAddress();
		sb.append('_')
		.append(addr.getCountry().getIsoCode())
		.append('_')
		.append(addr.getGovernorate().getId())
		.append('_');
		
		Delegation deleg = addr.getDelegation();
		sb.append(Objects.nonNull(deleg)? deleg.getId() : null);

		legalEntity.setIdentifier(sb.toString());
		
		return legalEntityRepository.save(legalEntity);
	}

	@Override
	public LegalEntity edit(LegalEntity legalEntity) {
		return this.add(legalEntity);
	}

	@Override
	public void remove(String identifier) {
		legalEntityRepository.deleteById(identifier);
	}

}
