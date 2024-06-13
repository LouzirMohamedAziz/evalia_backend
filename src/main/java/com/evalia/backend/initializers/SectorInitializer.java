package com.evalia.backend.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Sector;
import com.evalia.backend.repositories.SectorRepository;
import com.evalia.backend.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SectorInitializer implements Initializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsInitializer.class);

	private static final String JSON_FILE = "com/evalia/backend/sectors.json";

	private SectorRepository sectorRepository;

	public SectorInitializer(SectorRepository sectorRepository) {
		this.sectorRepository = sectorRepository;
	}

	@Override
	public void initialize() throws InitializationException {
		try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Sector>> recordTypes = new TypeReference<List<Sector>>() {
			};
			List<Sector> sectors = mapper.readValue(stream, recordTypes);
			sectorRepository.saveAll(sectors);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Sector.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}

	@Override
	public boolean isInitialized() throws InitializationException {
		return sectorRepository.count() > 0;
	}

}
