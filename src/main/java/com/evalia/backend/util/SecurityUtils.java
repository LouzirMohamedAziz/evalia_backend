package com.evalia.backend.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.evalia.backend.exceptions.ResourceNotFoundException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

	public static RSAPublicKey loadPublicKey(String path) throws ResourceNotFoundException, IOException {
		return RsaKeyConverters.x509()
				.convert(new ByteArrayInputStream(
						ResourceUtils.loadResource(path).readAllBytes()));
	}

	public static RSAPrivateKey loadPrivateKey(String path) throws ResourceNotFoundException, IOException {
		return RsaKeyConverters.pkcs8()
				.convert(new ByteArrayInputStream(
						ResourceUtils.loadResource(path).readAllBytes()));
	}

	public static JwtEncoder jwtEncoder(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		JWK jwk = new RSAKey.Builder(publicKey)
				.privateKey(privateKey).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	public static JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}

	public static Date tokenExpirationDate(Integer intervalInMinutes){

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		if(Objects.nonNull(intervalInMinutes)){
			intervalInMinutes += c.get(Calendar.MINUTE);
			c.set(Calendar.MINUTE, intervalInMinutes);
		}
		
		return c.getTime();
	}

}
