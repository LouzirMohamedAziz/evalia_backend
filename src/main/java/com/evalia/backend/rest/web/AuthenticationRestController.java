package com.evalia.backend.rest.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.evalia.backend.exceptions.ApiException;
import com.evalia.backend.models.VerificationToken;
import com.evalia.backend.security.auth.AuthenticationRequest;
import com.evalia.backend.security.auth.AuthenticationResponse;
import com.evalia.backend.security.auth.RegisterRequest;
import com.evalia.backend.security.controller.AuthenticationController;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    private final AuthenticationController authController;

    public AuthenticationRestController(AuthenticationController authController) {
        this.authController = authController;
    }

    @PostMapping("/token")
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        return authController.login(authenticationRequest);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest) {
        try {
            return authController.register(registerRequest);
        } catch (ApiException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/account/verification/send-token")
    public void requestEmailVerification(Authentication authentication) {
        try {
            String username = authentication.getName();
            authController.requestEmailVerification(username);
        } catch (AuthenticationException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/account/verification")
    public void verifyEmail(@RequestParam(name = "token") String token,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            authController.verifyEmail(token, username);
        } catch (AuthenticationException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/password/reset/send-token")
    public void requestPasswordRecovery(@RequestParam("email") String email) {
        try {
            authController.requestPasswordRecovery(email);
        } catch (ApiException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/password/reset/validate-token")
    public VerificationToken verifyPasswordRecoveryToken(@RequestParam("token") String token) {
        try {
            return authController.verifyPasswordRecoveryToken(token);
        } catch (ApiException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/password/reset")
    public void recoverPassword(@RequestParam(name = "token") String token,
            @RequestParam(name = "password") String password) {
        try {
            authController.recoverPassword(token, password);
        } catch (ApiException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public void logout(@RequestBody String entity) {
        //TODO: process POST request
        
    }
    
}
