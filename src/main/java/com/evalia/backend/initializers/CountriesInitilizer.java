package com.evalia.backend.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.CountryRepository;
import com.evalia.backend.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CountriesInitilizer implements Initializer{

	private static final Logger LOGGER = LoggerFactory.getLogger(CountriesInitilizer.class);
	private static final String JSON_FILE = "com/evalia/backend/countries.json";
	
	private CountryRepository countryRepository;
	
	
	@Autowired
	public CountriesInitilizer(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public void initialize() throws InitializationException{
		try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Country>> recordTypes = new TypeReference<List<Country>>() {};
			List<Country> countries = mapper.readValue(stream, recordTypes);
			countryRepository.saveAll(countries);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Country.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}
	

	@Override
	public boolean isInitialized() throws InitializationException {
		return countryRepository.count() > 0;
	}
}
