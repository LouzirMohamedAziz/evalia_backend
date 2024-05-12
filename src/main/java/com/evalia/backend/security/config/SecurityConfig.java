package com.evalia.backend.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.evalia.backend.repositories.ActorRepository;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${rsa.private-key}")
	private RSAPrivateKey privateKey;
	
	@Value("${rsa.public-key}")
	private RSAPublicKey publicKey;
	
	private final ActorRepository actorRepository;
	
	
	
	public SecurityConfig(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}
	
	@Bean
	public InMemoryUserDetailsManager users() {
	    return new InMemoryUserDetailsManager(
	            User.withUsername("admin")
	                    .password("{noop}admin")
	                    .authorities("ROLE_ADMIN")
	                    .build()
	    );
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}
	
	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(publicKey)
				.privateKey(privateKey).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	public SecurityFilterChain entityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.sessionManagement(configurer -> configurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.httpBasic(Customizer.withDefaults())
			.build();
	}
}