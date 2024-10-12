package com.evalia.backend.utils.initializers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.evalia.backend.exceptions.InitializationException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.Country;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.utils.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AminstratorInitializer implements Initializer{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityInitializer.class);
	private static final String JSON_FILE = "com/evalia/backend/administrator.json";

    @Autowired
    AccountRepository accountRepository;


    @Override
    public void initialize() throws InitializationException {
        try (InputStream stream = ResourceUtils.loadResource(JSON_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Account>> recordTypes = new TypeReference<List<Account>>() {};
            List<Account> accounts = mapper.readValue(stream, recordTypes);
            accountRepository.saveAll(accounts);
        }catch (IOException | ConstraintViolationException e) {
			InitializationException ex = InitializationException.build(Account.class.getName(), e);
			LOGGER.error("Could not pre-load admin account!", ex);
			throw ex;
		}
    }

    @Override
    public boolean isInitialized() throws InitializationException {
        return accountRepository.existsByEmail("administrator@evalia.com");
    }
    
}