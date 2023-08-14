package com.evalia.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.security.config.JwtTokenService;


@RestController
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtTokenService tokenService;
	
	@PostMapping("/administration/auth")
	public ResponseEntity<String> administrativeToken(Authentication authentication) {
		LOG.debug("Administration Portal: Token requested for user {}", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Administration Portal: Token generated {}", token);
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/services/auth")
	public ResponseEntity<String> entityToken(Authentication authentication) {
		LOG.debug("Entity Portal: Token requested for user {}", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Entity Portal: Token generated {}", token);
		return ResponseEntity.ok(token);
	}
}
