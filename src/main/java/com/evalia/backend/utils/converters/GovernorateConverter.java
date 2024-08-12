package com.evalia.backend.utils.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.GovernorateRepository;

@Component
public class GovernorateConverter implements Converter<Long, Governorate>{

	
	private final GovernorateRepository governorateRepository;
	
	public GovernorateConverter(GovernorateRepository governorateRepository) {
		this.governorateRepository = governorateRepository;
	}
	
	@Override
	public Governorate convert(Long source) {
		return governorateRepository.findById(source)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Governorate.class.getName(), String.valueOf(source)));
	}

}
