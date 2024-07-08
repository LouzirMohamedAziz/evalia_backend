package com.evalia.backend.converters;

import org.springframework.core.convert.converter.Converter;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.repositories.DelegationRepository;

public class DelegationConverter implements Converter<Long, Delegation>{

	
	private final DelegationRepository delegationRepository;
	
	public DelegationConverter(DelegationRepository countryRepository) {
		this.delegationRepository = countryRepository;
	}
	
	@Override
	public Delegation convert(Long source) {
		return delegationRepository.findById(source)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Delegation.class.getName(), String.valueOf(source)));
	}

}
