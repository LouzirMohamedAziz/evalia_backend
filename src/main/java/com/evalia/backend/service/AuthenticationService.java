package com.evalia.backend.service;

import org.springframework.security.core.Authentication;

import com.evalia.backend.models.Account;

public interface AuthenticationService {

	public String getToken(Authentication authentication);

	public void register(Account account);

	public Boolean verifyPasswordResetToken(String token);

}
