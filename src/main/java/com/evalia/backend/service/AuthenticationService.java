package com.evalia.backend.service;

import org.springframework.security.core.Authentication;

import com.evalia.backend.models.Account;
import com.evalia.backend.models.TokenType;

public interface AuthenticationService {

	public String getJWTToken(Authentication authentication);

	public void register(Account account);

	public boolean validatePRToken(String token);

	public void sendPasswordResetToken(String email);

	public void changeUserPassword(String email, String password, String token);

	public void sendEmailVerificationToken(String email);

	public void saveVerificationToken(Account account, String token, TokenType tokenType);

	public boolean validateEmailToken(String token);
}
