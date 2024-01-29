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
import com.evalia.backend.models.Indicator;
import com.evalia.backend.repositories.IndicatorRepository;
import com.evalia.backend.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: Hamdi Jandoubi
 */
@Component
public class IndicatorsInitializer implements Initializer{

private static final Logger LOGGER = LoggerFactory.getLogger(CountriesInitilizer.class);
	
	private static final String JSON_FILE = "com/evalia/backend/indicators.json";
	
	
	private IndicatorRepository indicatorRepository;
	
	
	@Autowired
	public IndicatorsInitializer(IndicatorRepository indicatorRepository) {
		this.indicatorRepository = indicatorRepository;
	}
	
	@Override
	public void initialize() throws InitializationException {
		try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Indicator>> recordTypes = new TypeReference<List<Indicator>>() {};
			List<Indicator> indicators = mapper.readValue(stream, recordTypes);
			indicatorRepository.saveAll(indicators);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Indicator.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}

	@Override
	public boolean isInitialized() throws InitializationException {
		return indicatorRepository.count() > 0;
	}
    
}