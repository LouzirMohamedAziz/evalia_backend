package com.evalia.backend.security.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.evalia.backend.models.Account;


@Service
public class JwtTokenService {
	
	private final JwtEncoder encoder;
	
	@Value("${security.jwt.expiration}")
	private int jwtExpiration;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	public JwtTokenService(JwtEncoder encoder) {
		this.encoder = encoder;
	}
	
	
	
	public String generateToken(Account account) {
		Instant now = Instant.now();
		String scope = account.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer(appName)
				.issuedAt(now)
				.expiresAt(now.plus(jwtExpiration, ChronoUnit.SECONDS))
				.subject(account.getUsername())
				.claim("scope", scope)
				.build();
		return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}