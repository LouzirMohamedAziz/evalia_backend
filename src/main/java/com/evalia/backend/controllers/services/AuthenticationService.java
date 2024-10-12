package com.evalia.backend.controllers.services;

import org.springframework.security.core.Authentication;

import com.evalia.backend.dto.TotpResponse;
import com.evalia.backend.models.Account;
import com.evalia.backend.utils.metadata.TokenType;
import com.evalia.backend.models.VerificationToken;

public interface AuthenticationService {

	
	/**
	 * generated a JWT token for the passed {@code authentication}.
	 * 
	 * @param authentication
	 * @return either a jwt token or a validation token
	 */
	public String login(Authentication authentication);
	
	
	/**
	 * login using the 2 factor authentication code.
	 * 
	 * @param token
	 * @return a jwt token
	 */
	public String login(String token, String mfCode);
	
	
	/**
	 * register a new account
	 * 
	 * @param account
	 */
	public void register(Account account);

	
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
	 *  if the {@code token} is valid then its associated {@code Account} 
	 *  is updated with the passed {@code password}.
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
	
	/**
	 * Enable 2 factor authentication for the passed account.
	 * 
	 * @param account
	 * @return
	 */
	public TotpResponse enable2FA(Account account);
	
	/**
	 * Verify Token for the passed TokenType.
	 * 
	 * @param Strin token
	 * @param TokenType tokenType
	 * @return
	 */
	public VerificationToken verifyToken(String token, TokenType tokenType);
}
