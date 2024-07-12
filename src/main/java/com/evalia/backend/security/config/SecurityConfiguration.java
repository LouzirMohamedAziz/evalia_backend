package com.evalia.backend.security.config;

import static org.springframework.http.HttpMethod.GET;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.evalia.backend.security.auth.JwtAuthenticationFilter;
import com.evalia.backend.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Value("${rsa.public-key}")
    private String publicKey;

    @Value("${rsa.private-key}")
    private String privateKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
            ).permitAll()
            .antMatchers(GET, "/api/countries/**", "/api/governorates/**").permitAll()
            .antMatchers("/auth/account/verification/**").authenticated()
            .antMatchers("/api/**").authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .oauth2ResourceServer()
            .jwt(jwt -> {
                try {
                    jwt.decoder(SecurityUtils.jwtDecoder(publicKey()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        
        return http.build();
    }

    @Bean
    public JwtEncoder passwordEncoder() throws Exception {
        return SecurityUtils.jwtEncoder(publicKey(), privateKey());
    }

    @Bean
    public JwtDecoder passwordDecoder() throws Exception {
        return SecurityUtils.jwtDecoder(publicKey());
    }

    @Bean
    public RSAPublicKey publicKey() throws Exception {
        return SecurityUtils.loadPublicKey(publicKey);
    }

    @Bean
    public RSAPrivateKey privateKey() throws Exception {
        return SecurityUtils.loadPrivateKey(privateKey);
    }
}
