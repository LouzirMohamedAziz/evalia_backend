package com.evalia.backend.rest.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.security.controller.AuthenticationController;
import com.evalia.backend.models.Account;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    AuthenticationController authController;

    public AccountRestController(AuthenticationController authController) {
        this.authController = authController;
    }
    
    @GetMapping
    public Account getAccountbyUsername(@RequestParam String username) {
        return authController.getAccountFromUsername(username);
    }


}
