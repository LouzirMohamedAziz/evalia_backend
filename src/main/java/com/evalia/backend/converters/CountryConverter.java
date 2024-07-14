package com.evalia.backend.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;

@Component
public class CountryConverter implements Converter<String, Country> {

	private final CountryRepository countryRepository;

	public CountryConverter(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public Country convert(String source) {
		return countryRepository.findById(source)
				.orElseThrow(() -> ResourceNotFoundException
						.build(Country.class.getName(), source));
	}
}
