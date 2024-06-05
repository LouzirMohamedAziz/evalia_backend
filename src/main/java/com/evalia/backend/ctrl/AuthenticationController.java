package com.evalia.backend.ctrl;

import java.util.Calendar;
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

import com.evalia.backend.exceptions.EmailVerificationException;
import com.evalia.backend.exceptions.TokenInvalidException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.TokenType;
import com.evalia.backend.models.VerificationToken;
import com.evalia.backend.repositories.AccountRepository;
import com.evalia.backend.repositories.ActorRepository;
import com.evalia.backend.repositories.VerificationTokenRepository;
import com.evalia.backend.security.services.JwtTokenService;
import com.evalia.backend.service.AuthenticationService;
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

	private boolean validPassword(String password) {
		return passwordPattern.matcher(password)
				.matches();
	}

	private String encodePassword(String password) {
		return passwordEnocder.encode(password);
	}

	@Transactional
	public void register(Account account) {

		String password = account.getPassword();

		if (!validPassword(password)) {
			throw new ConstraintViolationException(Constants.INVALID_PASSWORD,
					Set.of(ConstraintViolationImpl
							.forParameterValidation(
									Constants.INVALID_PASSWORD, null, null,
									Constants.INVALID_PASSWORD, Account.class,
									account, null, password, null, null, null, null)));
		}

		password = encodePassword(password);
		account.setPassword(password);
		actorRepository.save(account.getActor());
		accountRepository.save(account);
	}

	@Override
	public String getJWTToken(Authentication authentication) {
		LOG.debug("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		LOG.debug("Token granted: {}", token);
		return token;
	}

	@Override
	public boolean validatePRToken(String token) {

		final VerificationToken verificationToken = verificationTokenRepository.findByTokenAndTokenType(token,
				TokenType.PASSWORD_TOKEN);
		if (Objects.isNull(verificationToken) || SecurityUtils.isTokenExpired(verificationToken)) {
			return false;
		}

		return true;
	}

	@Override
	public void sendPasswordResetToken(String email) {

		Account account = accountRepository.findByEmail(email);
		if (Objects.nonNull(account)) {
			String token = UUID.randomUUID().toString();
			saveVerificationToken(account, token, TokenType.PASSWORD_TOKEN);
			emailService.sendEmail(account.getEmail(), Constants.RESET_TOKEN_MAIL_SUBJECT, token);
			return;
		}
		String msg = ResourceUtils.buildMessage("User with email {0} not found", email);
		throw new UsernameNotFoundException(msg);
	}

	@Override
	public void saveVerificationToken(Account account, String token, TokenType tokenType) {
		VerificationToken myToken = new VerificationToken(token,
				SecurityUtils.tokenExpirationDate(tokenExpirationInMinutes), account, tokenType);
		verificationTokenRepository.save(myToken);
	}

	@Override
	@Transactional
	public void changeUserPassword(String email, String password, String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByTokenAndTokenType(token,
				TokenType.PASSWORD_TOKEN);
		if (verificationToken.getAccount().getEmail().equals(email)) {
			Account account = verifyUserByEmail(email);

			if (!validPassword(password)) {
				throw new ConstraintViolationException(Constants.INVALID_PASSWORD,
						Set.of(ConstraintViolationImpl
								.forParameterValidation(
										Constants.INVALID_PASSWORD, null, null,
										Constants.INVALID_PASSWORD, Account.class,
										account, null, password, null, null, null, null)));
			}

			password = encodePassword(password);
			account.setPassword(password);
			accountRepository.save(account);
			verificationTokenRepository.delete(verificationToken);
			return;
		}
		throw TokenInvalidException.build(token);
	}

	@Override
	public void sendEmailVerificationToken(String email) {
		Account account = verifyUserByEmail(email);
		VerificationToken verificationToken = verificationTokenRepository.findByAccount_Email(email);
		if (Objects.nonNull(verificationToken))
			verificationTokenRepository.delete(verificationToken);
		if (!account.isEmailVerified()) {
			String token = UUID.randomUUID().toString();
			emailService.sendEmail(email, "Please verify your account by clicking on the linkin the email",
					"Verification Code : " + token);
			saveVerificationToken(account, token, TokenType.EMAIL_TOKEN);
			return;
		}
		throw EmailVerificationException.build(email);
	}

	@Override
	public boolean validateEmailToken(String token) {

		final VerificationToken verificationToken = verificationTokenRepository.findByTokenAndTokenType(token,
				TokenType.EMAIL_TOKEN);
		if (Objects.isNull(verificationToken) || SecurityUtils.isTokenExpired(verificationToken)) {
			return false;
		}
		verificationToken.getAccount().setEmailVerified(true);
		verificationTokenRepository.delete(verificationToken);
		return true;

	}

	private Account verifyUserByEmail(String email) {
		if (Objects.isNull(email) || email.isBlank()) {
			throw new IllegalArgumentException("Provided email is not valid!");
		}

		Account account = accountRepository.findByEmail(email);

		if (Objects.isNull(account)) {
			String msg = ResourceUtils.buildMessage("User with email {0} not found", email);
			throw new UsernameNotFoundException(msg);
		}
		return account;
	}

}
