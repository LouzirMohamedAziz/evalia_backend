package com.evalia.backend.utils.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Authority;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.AuthorityRepository;
import com.evalia.backend.utils.InitializersUtils;
import com.evalia.backend.utils.ResourceUtils;
import com.evalia.backend.utils.metadata.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthorityInitializer implements Initializer{

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityInitializer.class);
	private static final String JSON_FILE = "com/evalia/backend/authorities.json";
	
	@Autowired
    private AuthorityRepository authorityRepository;
	
	private List<Authority> getRoles(){
		return Arrays.asList(Role.values()).stream()
				.map(role -> {
                    var authority = new Authority();
                    authority.setRole(role);
                    return authority;
                })
				.collect(Collectors.toList());
	}

	@Override
	public void initialize() throws InitializationException {
		try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Authority>> recordTypes = new TypeReference<List<Authority>>() {};
			List<Authority> authorities = mapper.readValue(stream, recordTypes);
			authorityRepository.saveAll(authorities);
		} catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Authority.class.getName(), e);
			LOGGER.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}

	@Override
	public boolean isInitialized() throws InitializationException {
		// TODO Auto-generated method stub
		return authorityRepository.count() > 0;
	}

	
    
    
}
