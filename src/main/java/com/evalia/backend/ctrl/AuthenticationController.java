package com.evalia.backend.ctrl;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evalia.backend.ctrl.services.AuthenticationService;
import com.evalia.backend.exceptions.TokenExpiredException;
import com.evalia.backend.exceptions.TokenInvalidException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.TokenType;
import com.evalia.backend.models.VerificationToken;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.ActorRepository;
import com.evalia.backend.repositories.VerificationTokenRepository;
import com.evalia.backend.security.services.JwtTokenService;
import com.evalia.backend.service.EmailService;
import com.evalia.backend.util.Constants;
import com.evalia.backend.util.ResourceUtils;
import com.evalia.backend.util.SecurityUtils;

@Service
public class AuthenticationController implements AuthenticationService {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

	@Value("${evalia.security.passwordToken.expiration}")
	private Integer tokenExpirationInMinutes;

	private final VerificationTokenRepository verificationTokenRepository;
	private final JwtTokenService tokenService;
	private final BCryptPasswordEncoder passwordEnocder;
	private final AccountRepository accountRepository;
	private final ActorRepository actorRepository;
	private final Pattern passwordPattern;
	private final EmailService emailService;

	public AuthenticationController(VerificationTokenRepository verificationTokenRepository,
			JwtTokenService tokenService,
			BCryptPasswordEncoder passwordEnocder,
			AccountRepository accountRepository,
			ActorRepository actorRepository,
			EmailService emailService) {
		this.verificationTokenRepository = verificationTokenRepository;
		this.tokenService = tokenService;
		this.passwordEnocder = passwordEnocder;
		this.accountRepository = accountRepository;
		this.actorRepository = actorRepository;
		this.emailService = emailService;
		this.passwordPattern = Pattern.compile(Constants.PASSWORD_REGEX);
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
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> {
					String msg = ResourceUtils.buildMessage("User with email {0} not found", email);
					return new UsernameNotFoundException(msg);
				});
		return account;
	}

	public Account getAccountFromUsername(String username) {
		if (Objects.isNull(username) || username.isBlank()) {
			throw new IllegalArgumentException("Provided username is not valid!");
		}
		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> {
					String msg = ResourceUtils.buildMessage("User with username {0} not found", username);
					return new UsernameNotFoundException(msg);
				});
		return account;
	}

	@Override
	public String login(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Token granted: {}", token);
		return token;
	}

	@Override
	@Transactional
	public void register(Account account) {
		String password = account.getPassword();
		password = encodePassword(password);
		account.setPassword(password);
		if (account.isUsingMfa()) {
			account.setSecret("");
		}
		actorRepository.save(account.getActor());
		accountRepository.save(account);
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
}
