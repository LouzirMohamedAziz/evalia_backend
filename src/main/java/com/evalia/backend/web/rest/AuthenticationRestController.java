package com.evalia.backend.web.rest;


import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.evalia.backend.ctrl.AuthenticationController;
import com.evalia.backend.metadata.ActorType;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.Actor;

@RestController
public class AuthenticationRestController {

    private final AuthenticationController authController;

    @Autowired
    public AuthenticationRestController(AuthenticationController authController) {
        this.authController = authController;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return authController.getToken(authentication);
    }
    
    @PostMapping("/register")
    public void register(@RequestParam("type") ActorType actorType,
    		@RequestBody Account account) {
    	
    	if(ActorType.CIVIL.equals(actorType) ||
    			ActorType.PROFESSIONAL.equals(actorType)) {
    		authController.register(account);
    		return;
    	}
    	String message = "Actor type {0} is undefined!";
    	message = MessageFormat.format(message, actorType);
    	throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
    }
	
}
