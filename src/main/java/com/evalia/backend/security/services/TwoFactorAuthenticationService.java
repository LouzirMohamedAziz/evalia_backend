package com.evalia.backend.security.services;

public interface TwoFactorAuthenticationService {

    public String generateNewSecret();

    public String generateQrCodeImageUri(String secret);

    public boolean isOtpValid(String secret, String code);

    public boolean isOtpNotValid(String secret, String code);
}


