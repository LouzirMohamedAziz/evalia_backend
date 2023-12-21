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
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.repositories.CountryRepository;
import com.evalia.backend.util.InitializersUtils;
import com.evalia.backend.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CountriesInitilizer extends Initializer<Country>{

	private static final Logger LOGGER = LoggerFactory.getLogger(CountriesInitilizer.class);
	
	private static final String JSON_FILE = "com/evalia/backend/countries.json";
	
	@Autowired
	private CountryRepository repository;

	private void createRelation(List<Country> countries) {
		for(Country country: countries) {
			for(Governorate governorate: country.getGovernorates()) {
				for(Delegation delegation: governorate.getDelegations()) {
					delegation.setGovernorate(governorate);
				}
				governorate.setCountry(country);
			}
		}
	}
	
	@Override
	void preLoad(Class<Country> clazz, String filePath) {
		try (InputStream stream = ResourceUtils.loadResource(filePath)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Country>> recordTypes = new TypeReference<List<Country>>() {};
			List<Country> countries = mapper.readValue(stream, recordTypes);
			createRelation(countries);
			repository.saveAll(countries);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(clazz.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if (!InitializersUtils.isLoaded(repository)) {
			preLoad(Country.class, JSON_FILE);
		}
	}
}
