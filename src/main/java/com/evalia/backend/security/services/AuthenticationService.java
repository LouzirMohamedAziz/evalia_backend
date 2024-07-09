package com.evalia.backend.security.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evalia.backend.models.Account;
import com.evalia.backend.models.VerificationToken;
import com.evalia.backend.security.auth.AuthenticationRequest;
import com.evalia.backend.security.auth.AuthenticationResponse;
import com.evalia.backend.security.auth.RegisterRequest;
import com.evalia.backend.security.auth.VerificationRequest;

public interface AuthenticationService {

	/**
	 * generated a JWT token for the passed {@code authentication}.
	 * 
	 * @param authentication
	 * @return a jwt token
	 */
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

	/**
	 * register a new account
	 * 
	 * @param account
	 */
	public AuthenticationResponse register(RegisterRequest registerRequest);

	/**
	 * if {@code username}'s email is not verified, then invoking this method
	 * sends a verification token to {@code Account.email} via mail.
	 * 
	 * @param email
	 */
	public void requestEmailVerification(String username);

	/**
	 * Verifies an {@code Account}'s associated email using the passed
	 * {@code token}.
	 * 
	 * @param token
	 */
	public void verifyEmail(String token, String username);

	/**
	 * if {@code email} is associated with an account and is verified,
	 * then invoking this method sends a password recovery token to {@code email}
	 * via mail.
	 * 
	 * @param email
	 */
	public void requestPasswordRecovery(String email);

	/**
	 * Verify the passed token is still valid to use.
	 * 
	 * @param token
	 * @return {@code VerificationToken} instance of {@code token} if valid.
	 */
	public VerificationToken verifyPasswordRecoveryToken(String token);

	/**
	 * if the {@code token} is valid then its associated {@code Account}
	 * is updated with the passed {@code password}.
	 * 
	 * @param password
	 * @param token
	 */
	public void recoverPassword(String token, String password);

	/**
	 * Updates {@code account} with {@code password} if it meets
	 * the standard rules.
	 * 
	 * @param password
	 */
	public void updatePassword(Account account, String password);

	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException;

	public AuthenticationResponse verifyTotpCode(VerificationRequest verificationRequest);
}
