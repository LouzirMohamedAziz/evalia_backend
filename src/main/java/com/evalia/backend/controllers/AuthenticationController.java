package com.evalia.backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.security.services.JwtTokenService;

@RestController
public class AuthenticationController {

	
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final JwtTokenService tokenService;

    public AuthenticationController(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return token;
    }
	
}
