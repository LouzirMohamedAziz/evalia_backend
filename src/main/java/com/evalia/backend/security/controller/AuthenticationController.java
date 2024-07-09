package com.evalia.backend.security.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evalia.backend.ctrl.services.EmailService;
import com.evalia.backend.exceptions.TokenExpiredException;
import com.evalia.backend.exceptions.TokenInvalidException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.TokenType;
import com.evalia.backend.models.VerificationToken;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.ActorRepository;
import com.evalia.backend.repositories.VerificationTokenRepository;
import com.evalia.backend.security.auth.AuthenticationRequest;
import com.evalia.backend.security.auth.AuthenticationResponse;
import com.evalia.backend.security.auth.RegisterRequest;
import com.evalia.backend.security.auth.VerificationRequest;
import com.evalia.backend.security.services.AuthenticationService;
import com.evalia.backend.security.services.JwtTokenService;
import com.evalia.backend.util.Constants;
import com.evalia.backend.util.ResourceUtils;
import com.evalia.backend.util.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthenticationController implements AuthenticationService {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Value("${evalia.security.passwordToken.expiration}")
	private Integer tokenExpirationInMinutes;

	private final VerificationTokenRepository verificationTokenRepository;
	private final JwtTokenService jwtTokenService;
	private final BCryptPasswordEncoder passwordEnocder;
	private final AccountRepository accountRepository;
	private final ActorRepository actorRepository;
	private final Pattern passwordPattern;
	private final EmailService emailService;
	private final TwoFactorAuthenticationController tfaController;
    private final AuthenticationManager authenticationManager;

	public AuthenticationController(VerificationTokenRepository verificationTokenRepository,
			JwtTokenService jwtTokenService,
			BCryptPasswordEncoder passwordEnocder,
			AccountRepository accountRepository,
			ActorRepository actorRepository,
			EmailService emailService,
			TwoFactorAuthenticationController tfaController,
			AuthenticationManager authenticationManager) {
		this.verificationTokenRepository = verificationTokenRepository;
		this.jwtTokenService = jwtTokenService;
		this.passwordEnocder = passwordEnocder;
		this.accountRepository = accountRepository;
		this.actorRepository = actorRepository;
		this.emailService = emailService;
		this.passwordPattern = Pattern.compile(Constants.PASSWORD_REGEX);
		this.tfaController = tfaController;
		this.authenticationManager= authenticationManager;
	}

	private boolean isPasswordValid(String password) {
		return passwordPattern.matcher(password)
				.matches();
	}

	private String encodePassword(String password) {
		if (!isPasswordValid(password)) {
			throw new ConstraintViolationException(Constants.INVALID_PASSWORD,
					Set.of(ConstraintViolationImpl
							.forParameterValidation(
									Constants.INVALID_PASSWORD, null, null,
									Constants.INVALID_PASSWORD, Account.class,
									null, null, password, null, null, null, null)));
		}
		return passwordEnocder.encode(password);
	}

	private Long clearTokens(String email) {
		Objects.requireNonNull(email);
		if (!email.isBlank()) {
			return verificationTokenRepository.deleteByAccount_Email(email);
		}
		return 0L;
	}

	private void registerToken(Account account, String token, TokenType tokenType) {
		Date expiryDate = SecurityUtils.generateExpirationDate(tokenExpirationInMinutes);
		VerificationToken myToken = new VerificationToken(token, expiryDate, account, tokenType);
		verificationTokenRepository.save(myToken);
	}

	private Account getAccountFromEmail(String email) {
		if (Objects.isNull(email) || email.isBlank()) {
			throw new IllegalArgumentException("Provided email is not valid!");
		}
		return accountRepository.findByEmail(email)
				.orElseThrow(() -> {
					String msg = ResourceUtils.buildMessage("User with email {0} not found", email);
					return new UsernameNotFoundException(msg);
				});
	}

	public Account getAccountFromUsername(String username) {
		if (Objects.isNull(username) || username.isBlank()) {
			throw new IllegalArgumentException("Provided username is not valid!");
		}
		return accountRepository.findByUsername(username)
				.orElseThrow(() -> {
					String msg = ResourceUtils.buildMessage("User with username {0} not found", username);
					return new UsernameNotFoundException(msg);
				});
	}

	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword()
                )
        );
		String authName = authenticationRequest.getUsername();
		Account account = getAccountFromUsername(authName);
        if (account.isMfaEnabled()) {
            return AuthenticationResponse.builder()
                    .accessToken("")
                    .refreshToken("")
                    .mfaEnabled(true)
                    .build();
        }
		String jwtToken = jwtTokenService.generateToken(account);
		LOG.debug("jwtToken granted: {}", jwtToken);
		String refreshToken = jwtTokenService.generateRefreshToken(account);
		LOG.debug("Refresh Token granted: {}", refreshToken);
        return AuthenticationResponse.builder()
				.secretImageUri(tfaController.generateQrCodeImageUri(account.getSecret()))
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.mfaEnabled(account.isMfaEnabled())
				.build();
	}

	/*
	 * TO-DO return authentication response in cas mfa enabled to send the secret
	 * generated
	 */
	@Override
	@Transactional
	public AuthenticationResponse register(RegisterRequest registerRequest) {
		String password = registerRequest.getPassword();
		password = encodePassword(password);
		registerRequest.setPassword(password);
		Account account = new Account();
				account.setUsername(registerRequest.getUsername());
				account.setEmail(registerRequest.getEmail());
				account.setPassword(password);
				account.setActor(registerRequest.getActor());
				account.setMfaEnabled(registerRequest.isMfaEnabled());
		if (account.isMfaEnabled()) {
			account.setSecret(tfaController.generateNewSecret());
		}
		actorRepository.save(account.getActor());
		accountRepository.save(account);
		String jwtToken = jwtTokenService.generateToken(account);
		LOG.debug("JWT Token generated");
		String refreshToken = jwtTokenService.generateRefreshToken(account);
		LOG.debug("Refresh Token Generated");
		return AuthenticationResponse.builder()
				.secretImageUri(tfaController.generateQrCodeImageUri(account.getSecret()))
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.mfaEnabled(account.isMfaEnabled())
				.build();
	}

	@Override
	@Transactional
	public void requestEmailVerification(String username) {
		Account account = getAccountFromUsername(username);
		String email = account.getEmail();
		clearTokens(email);

		if (!account.isAccountNonLocked()) {
			String msg = ResourceUtils.buildMessage("Account with email {0} is locked", email);
			throw new SecurityException(msg);
		}

		if (account.isEmailVerified()) {
			String msg = ResourceUtils.buildMessage("Email {0} is already verified", email);
			throw new SecurityException(msg);
		}

		String token = UUID.randomUUID().toString();
		emailService.sendEmail(email,
				"Please verify your account by clicking on the lin in the email",
				"Verification Code : " + token);
		registerToken(account, token, TokenType.EMAIL_TOKEN);
	}

	@Override
	public void verifyEmail(String token, String username) {
		final VerificationToken verificationToken = verificationTokenRepository
				.findByTokenAndTokenType(token, TokenType.EMAIL_TOKEN)
				.orElseThrow(() -> TokenInvalidException.build(token));

		Account account = verificationToken.getAccount();
		if (!getAccountFromUsername(username).equals(account)) {
			throw TokenInvalidException.build(token);
		}
		if (SecurityUtils.isTokenExpired(verificationToken)) {
			throw TokenExpiredException.build(token);
		}

		account.setEmailVerified(true);
		accountRepository.save(account);
		verificationTokenRepository.delete(verificationToken);
	}

	@Override
	@Transactional
	public void requestPasswordRecovery(String email) {
		Account account = getAccountFromEmail(email);
		clearTokens(email);

		if (!account.isAccountNonLocked()) {
			String msg = ResourceUtils.buildMessage("Account with email {0} is locked", email);
			throw new SecurityException(msg);
		}

		if (!account.isEmailVerified()) {
			String msg = ResourceUtils.buildMessage("Email {0} is not verified", email);
			throw new SecurityException(msg);
		}

		String token = UUID.randomUUID().toString();
		registerToken(account, token, TokenType.PASSWORD_TOKEN);
		emailService.sendEmail(account.getEmail(), Constants.RESET_TOKEN_MAIL_SUBJECT, token);
	}

	@Override
	public VerificationToken verifyPasswordRecoveryToken(String token) {
		final VerificationToken verificationToken = verificationTokenRepository
				.findByTokenAndTokenType(token, TokenType.PASSWORD_TOKEN)
				.orElseThrow(() -> TokenInvalidException.build(token));
		if (SecurityUtils.isTokenExpired(verificationToken)) {
			throw TokenExpiredException.build(token);
		}
		return verificationToken;
	}

	@Override
	@Transactional
	public void recoverPassword(String token, String password) {
		VerificationToken verificationToken = verifyPasswordRecoveryToken(token);
		Account account = verificationToken.getAccount();
		updatePassword(account, password);
		verificationTokenRepository.delete(verificationToken);
	}

	@Override
	public void updatePassword(Account account, String password) {
		password = encodePassword(password);
		account.setPassword(password);
		accountRepository.save(account);
	}

	@Override
	public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String username;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		username = jwtTokenService.extractUsername(refreshToken);
		if (username != null) {
			Account account = accountRepository.findById(username)
					.orElseThrow();
			if (jwtTokenService.isTokenValid(refreshToken, account)) {
				var accessToken = jwtTokenService.generateToken(account);
				var authResponse = AuthenticationResponse.builder()
						.accessToken(accessToken)
						.refreshToken(refreshToken)
						.mfaEnabled(false)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
	@Override
	public AuthenticationResponse verifyTotpCode(
			VerificationRequest verificationRequest) {
		var account = accountRepository.findById(verificationRequest.getUsername())
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("No user found with %S", verificationRequest.getUsername())));		
		if (tfaController.isOtpNotValid(account.getSecret(), verificationRequest.getCode())) {

			throw new BadCredentialsException("Code is not correct");
		}
		var jwtToken = jwtTokenService.generateToken(account);
		return AuthenticationResponse.builder()
				.accessToken(jwtToken)
				.mfaEnabled(account.isMfaEnabled())
				.build();
	}
}
