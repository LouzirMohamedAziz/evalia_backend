package com.evalia.backend.controllers.services;

import com.evalia.backend.dto.TotpResponse;

public interface TOTPService {

	public String generateNewSecret();

	public TotpResponse generateQrCodeImageUri(String secret);

	public boolean isOtpValid(String secret, String code);

	public boolean isOtpNotValid(String secret, String code);

}
