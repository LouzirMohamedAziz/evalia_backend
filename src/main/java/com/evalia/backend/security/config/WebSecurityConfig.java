package com.evalia.backend.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.evalia.backend.repositories.AdministrativeRepository;
import com.evalia.backend.repositories.EntityRepository;
import com.evalia.backend.security.services.AdministrativeUserDetailsServiceImpl;
import com.evalia.backend.security.services.EntityUserDetailsServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
//	@Autowired
//	private BasicAuthenticationEntryPoint basicAuthEntryPoint;
//	
//	@Autowired
//	private BearerTokenAuthenticationEntryPoint bearerAuthEntryPoint;
	
	@Autowired
	private AdministrativeRepository administrativeRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private RSAPublicKey publicKey;
	
	@Autowired
	private RSAPrivateKey privateKey;

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
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationEntryPoint configureAdministrationEntryPoint() {
		BasicAuthenticationEntryPoint authEntryPoint = new BasicAuthenticationEntryPoint();
		authEntryPoint.setRealmName("administration realm");
		return authEntryPoint;
	}
	
	@Bean
	AuthenticationEntryPoint configureEntityEntryPoint() {
		BearerTokenAuthenticationEntryPoint authEntryPoint = new BearerTokenAuthenticationEntryPoint();
		authEntryPoint.setRealmName("entity realm");
		return authEntryPoint;
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain entityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.defaultAuthenticationEntryPointFor(configureEntityEntryPoint(),
					new AntPathRequestMatcher("/services/**"))
			.and()
			.userDetailsService(
					new EntityUserDetailsServiceImpl(entityRepository))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.sessionManagement(configurer -> configurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic();
		return http.build();
	}
	
	@Bean
	@Order(2)
	public SecurityFilterChain administrationFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.defaultAuthenticationEntryPointFor(configureEntityEntryPoint(),
					new AntPathRequestMatcher("/administration/**"))
			.and()
			.userDetailsService(
					new AdministrativeUserDetailsServiceImpl(administrativeRepository))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.sessionManagement(configurer -> configurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic();
		return http.build();
	}
}