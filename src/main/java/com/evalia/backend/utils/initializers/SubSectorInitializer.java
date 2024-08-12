package com.evalia.backend.utils.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Sector;
import com.evalia.backend.models.SubSector;
import com.evalia.backend.repositories.SectorRepository;
import com.evalia.backend.repositories.SubSectorRepository;
import com.evalia.backend.utils.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SubSectorInitializer implements Initializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubSectorInitializer.class);

	private static final String JSON_FILE = "com/evalia/backend/subsectors.json";

	private SubSectorRepository subSectorRepository;
	private SectorRepository sectorRepository;

	public SubSectorInitializer(SubSectorRepository subSectorRepository, SectorRepository sectorRepository) {
		this.subSectorRepository = subSectorRepository;
		this.sectorRepository = sectorRepository;
	}

	@Override
	public void initialize() throws InitializationException {
		try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<SubSector>> recordTypes = new TypeReference<List<SubSector>>() {
			};
			List<SubSector> subSectors = mapper.readValue(stream, recordTypes);

			for (SubSector subSector : subSectors) {
				Sector sector = sectorRepository.findById(subSector.getSector().getName())
						.orElseThrow(() -> new InitializationException(
								"Sector not found for name: " + subSector.getSector().getName()));
				subSector.setSector(sector);
			}

			subSectorRepository.saveAll(subSectors);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(SubSector.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}

	@Override
	public boolean isInitialized() throws InitializationException {
		return subSectorRepository.count() > 0;
	}
}
