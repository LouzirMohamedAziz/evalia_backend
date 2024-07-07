package com.evalia.backend.security.controller;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evalia.backend.security.services.TwoFactorAuthenticationService;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
@Service
public class TwoFactorAuthenticationController implements TwoFactorAuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(TwoFactorAuthenticationController.class);

    @Override
    public String generateNewSecret() {
        return new DefaultSecretGenerator().generate();
    }

    @Override
    public String generateQrCodeImageUri(String secret) {
        QrData data = new QrData.Builder()
                .label("Alibou Coding 2FA example")
                .secret(secret)
                .issuer("Alibou-Coding")
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = new byte[0];
        try {
            imageData = generator.generate(data);
        } catch (QrGenerationException e) {
            e.printStackTrace();
            LOG.error("Error while generating QR-CODE");
        }

        return getDataUriForImage(imageData, generator.getImageMimeType());
    }

    @Override
    public boolean isOtpValid(String secret, String code) {
        TimeProvider timeProvider = new SystemTimeProvider();
        DefaultCodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return verifier.isValidCode(secret, code);
    }

    @Override
    public boolean isOtpNotValid(String secret, String code) {
        return !this.isOtpValid(secret, code);
    }

}
