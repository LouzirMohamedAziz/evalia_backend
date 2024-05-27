package com.evalia.backend.ctrl;


import javax.validation.Valid;

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


@Service
public class AuthenticationController implements AuthenticationService{
	
	
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
    
    
    private final JwtTokenService tokenService;
    private final BCryptPasswordEncoder passwordEnocder;
    
    private final AccountRepository accountRepository;
    
    private final ActorRepository actorRepository;

    @Autowired
    public AuthenticationController(JwtTokenService tokenService,
    		BCryptPasswordEncoder passwordEnocder,
    		AccountRepository accountRepository,
    		ActorRepository actorRepository) {
        this.tokenService = tokenService;
        this.passwordEnocder = passwordEnocder;
        this.accountRepository = accountRepository;
        this.actorRepository = actorRepository;
    }
    
    
    private void encodePassword(Account account) {
    	String password = account.getPassword();
		password = passwordEnocder.encode(password);
		account.setPassword(password);
    }
    
    
    public void register(@Valid Account account) {
    	encodePassword(account);
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
