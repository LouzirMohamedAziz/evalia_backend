package com.evalia.backend.initializers;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.Administrative;
import com.evalia.backend.repositories.AdministrativeRepository;
import com.evalia.backend.util.InitializersUtils;
import com.evalia.backend.util.ResourceUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(1)
public class AdministrativesInitializer extends Initializer<Administrative>{
	
	private static final Logger logger = LoggerFactory.getLogger(AdministrativesInitializer.class);

	private static final String JSON_FILE = "com/evalia/backend/administrator.json";
	
	@Autowired
	PasswordEncoder encode;
	
	@Autowired
	AdministrativeRepository repository;

	@Override
	protected void preLoad(Class<Administrative> clazz, String filePath) {
		//TODO see why the relation between account and administrator cannot be deleted
		try (InputStream stream = ResourceUtils.loadResource(filePath)) {
			ObjectMapper mapper = new ObjectMapper();
			Administrative administrator = mapper.readValue(stream, Administrative.class);
			Account acc = administrator.getAccount();
			acc.setPassword(encode.encode(acc.getPassword()));
			acc.setAdministrative(administrator);
			repository.save(administrator);
		} catch (IOException e) {
			InitializationException ex = InitializationException.build(clazz.getName(), e);
			logger.error("Could not pre-load entity!", ex);
			throw ex;
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		if(!InitializersUtils.isLoaded(repository)) {
			preLoad(Administrative.class, JSON_FILE);
		}
	}
}
