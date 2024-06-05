package com.evalia.backend.web.rest;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.evalia.backend.ctrl.AuthenticationController;
import com.evalia.backend.exceptions.TokenInvalidException;
import com.evalia.backend.metadata.ActorType;
import com.evalia.backend.models.Account;

@RestController
public class AuthenticationRestController {

    private final AuthenticationController authController;

    public AuthenticationRestController(AuthenticationController authController) {
        this.authController = authController;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return authController.getJWTToken(authentication);
    }

    @PostMapping("/register")
    public void register(@RequestParam("type") ActorType actorType,
            @RequestBody Account account) {

        if (ActorType.CIVIL.equals(actorType) ||
                ActorType.PROFESSIONAL.equals(actorType)) {
            authController.register(account);
            return;
        }
        String message = "Actor type {0} is undefined!";
        message = MessageFormat.format(message, actorType);
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, message);
    }

    @PostMapping("/sendResetToken")
    public void sendResetToken(@RequestParam("email") String email) {
        try {
            authController.sendPasswordResetToken(email);
        } catch (UsernameNotFoundException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/verifyResetToken")
    public void verifyResetToken(@RequestParam String token) {
        if (!authController.validatePRToken(token)) {
            throw new TokenInvalidException(token);
        }
    }

    @PostMapping("/updatePassword")
    public void savePassword(@RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "token") String token) {

        try {
            verifyResetToken(token);
            authController.changeUserPassword(email, password, token);

        } catch (AuthenticationException | IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/verifyEmail")
    public void verifyAccount(@RequestParam(name = "email") String email) {
        try {
            authController.sendEmailVerificationToken(email);
        } catch (AuthenticationException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/validateEmail")
    public void validateEmail(@RequestParam(name = "token") String token,
            @RequestParam(name = "email") String email) {
        if (!authController.validateEmailToken(token)) {
            throw new TokenInvalidException(token);
        }
    }

}
