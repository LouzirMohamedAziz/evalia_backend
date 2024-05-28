package com.evalia.backend.ctrl;


import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Account;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.ActorRepository;
import com.evalia.backend.security.services.JwtTokenService;
import com.evalia.backend.service.AuthenticationService;
import com.evalia.backend.util.Constants;


@Service
public class AuthenticationController implements AuthenticationService{
	
	
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    
    private final JwtTokenService tokenService;
    private final BCryptPasswordEncoder passwordEnocder;
    private final AccountRepository accountRepository;
    private final ActorRepository actorRepository;
    private final Pattern passwordPattern;

    @Autowired
    public AuthenticationController(JwtTokenService tokenService,
    		BCryptPasswordEncoder passwordEnocder,
    		AccountRepository accountRepository,
    		ActorRepository actorRepository) {
        this.tokenService = tokenService;
        this.passwordEnocder = passwordEnocder;
        this.accountRepository = accountRepository;
        this.actorRepository = actorRepository;
        this.passwordPattern = Pattern.compile(Constants.PASSWORD_REGEX);
    }
    
    
    private boolean validPassword(String password) {
    	return passwordPattern.matcher(password)
    			.matches();
    }
    
    private String encodePassword(String password) {
		return passwordEnocder.encode(password);
    }
    
    
    @Transactional
    public void register(@Valid Account account) {
    	
    	String password = account.getPassword();
    	
    	if(!validPassword(password)){
    		throw new ConstraintViolationException(Constants.INVALID_PASSWORD,
    				Set.of(ConstraintViolationImpl
    						.forParameterValidation(
    								Constants.INVALID_PASSWORD, null, null,
    								Constants.INVALID_PASSWORD, Account.class,
    								account, null, password, null, null, null, null)
    						)
    				);
    	}
    	
    	password = encodePassword(password);
    	account.setPassword(password);
    	actorRepository.save(account.getActor());
    	accountRepository.save(account);
    }


	@Override
	public String getToken(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
	}
	
}
