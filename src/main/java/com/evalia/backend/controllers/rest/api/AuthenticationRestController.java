package com.evalia.backend.controllers.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.evalia.backend.controllers.impl.AuthenticationController;
import com.evalia.backend.dto.TotpResponse;
import com.evalia.backend.exceptions.ApiException;
import com.evalia.backend.models.Account;
import com.evalia.backend.models.VerificationToken;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    private final AuthenticationController authController;

    public AuthenticationRestController(AuthenticationController authController) {
        this.authController = authController;
    }

    @PostMapping("/token")
    public String login(Authentication authentication) {
        return authController.login(authentication);
    }

    @PostMapping("/register")
    public void register(@RequestBody Account account) {
        try {
            authController.register(account);
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

    @PostMapping("/mfalogin")
    public String mfaLogin(@RequestParam(name = "token") String token,
            @RequestParam(name = "mfaCode") String mfaCode) {
        try {
            return authController.login(token, mfaCode);
        } catch (ApiException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/enable2fa")
    public ResponseEntity<TotpResponse> enable2FA(Authentication authentication) {
        Account account = authController.getAccountFromUsername(authentication.getName());
        try {
            TotpResponse totpResponse = authController.enable2FA(account);
            return ResponseEntity.ok()
                    .body(totpResponse);
        } catch (SecurityException e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
