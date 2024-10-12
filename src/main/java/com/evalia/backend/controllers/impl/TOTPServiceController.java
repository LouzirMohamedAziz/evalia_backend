package com.evalia.backend.controllers.impl;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evalia.backend.controllers.services.TOTPService;
import com.evalia.backend.dto.TotpResponse;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;

@Service
public class TOTPServiceController implements TOTPService {

    private static final Logger LOG = LoggerFactory.getLogger(TOTPServiceController.class);
    
    private final SecretGenerator secretGenerator;
    private final CodeVerifier codeVerifier;
    private final QrGenerator qrGenerator;
    
    
    public TOTPServiceController() {
    	this.secretGenerator = new DefaultSecretGenerator();
    	
    	TimeProvider timeProvider = new SystemTimeProvider();
        DefaultCodeGenerator codeGenerator = new DefaultCodeGenerator();
        
        this.codeVerifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        this.qrGenerator = new ZxingPngQrGenerator();
    }

    @Override
    public String generateNewSecret() {
        return secretGenerator.generate();
    }

    @Override
    public TotpResponse generateQrCodeImageUri(String secret) {
    	
        QrData data = new QrData.Builder()
                .label("Evalia Account")
                .secret(secret)
                .issuer("Evalia")
                .algorithm(HashingAlgorithm.SHA256)
                .digits(6)
                .period(30)
                .build();

        try {
        	byte[] imageData = qrGenerator.generate(data);
            TotpResponse totpResponse = new TotpResponse();
            totpResponse.setSecret(secret);
            totpResponse.setType(qrGenerator.getImageMimeType());
            totpResponse.setQr(Base64.getEncoder().encodeToString(imageData));
            return totpResponse;
        } catch (QrGenerationException e) {
            LOG.error("Error while generating QR-CODE", e);
            throw new SecurityException(e);
        }
    }

    @Override
    public boolean isOtpValid(String secret, String code) {
        return codeVerifier.isValidCode(secret, code);
    }

    @Override
    public boolean isOtpNotValid(String secret, String code) {
        return !this.isOtpValid(secret, code);
    }
}
