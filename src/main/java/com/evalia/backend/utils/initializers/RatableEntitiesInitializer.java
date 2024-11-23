package com.evalia.backend.utils.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Ratable;
import com.evalia.backend.repositories.RatableRepository;
import com.evalia.backend.utils.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//@Component
public class RatableEntitiesInitializer implements Initializer{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityInitializer.class);
	private static final String JSON_FILE = "com/evalia/backend/entities.json";

    RatableRepository ratableRepository;
    
    @Autowired
    public RatableEntitiesInitializer(RatableRepository ratableRepository){
        this.ratableRepository = ratableRepository;

    }

    @Override
    public void initialize(){
        try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Ratable>> recordTypes = new TypeReference<List<Ratable>>() {};
			List<Ratable> ratables = mapper.readValue(stream, recordTypes);
			ratableRepository.saveAll(ratables);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Ratable.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
    }

    @Override
    public boolean isInitialized() throws InitializationException {
        return ratableRepository.count() >0;
    }

}
