package ignore.com.evalia.backend.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.evalia.backend.security.auth.JwtAuthenticationFilter;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Value("spring boot")
	private String alia;
	
	@Value("${rsa.private-key}")
	private RSAPrivateKey privateKey;
	
	@Value("${rsa.public-key}")
	private RSAPublicKey publicKey;

	private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
	
//	private final ActorRepository actorRepository;
//	
//	
//	
//	public SecurityConfig(ActorRepository actorRepository) {
//		this.actorRepository = actorRepository;
//	}
	
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
	
	@Order(1)
	@Bean
	public SecurityFilterChain apisEntryPoint(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
			.antMatcher("/api/**")
			.authorizeHttpRequests()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement(configurer -> configurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.build();
	}
	
	@Order(2)
	@Bean
	public SecurityFilterChain authenticationEntryPoint(HttpSecurity http, InMemoryUserDetailsManager userDetailsService) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
			.antMatcher("/token")
			.authorizeHttpRequests()
			.antMatchers("/token")
			.authenticated()
			.and()
			.sessionManagement(configurer -> configurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.userDetailsService(userDetailsService)
			.authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.logout(logout -> logout.logoutUrl("/api/auth/logout")
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler((requested , response, authentication) -> SecurityContextHolder.clearContext()))
			.httpBasic(Customizer.withDefaults())
			.build();
	}
}
