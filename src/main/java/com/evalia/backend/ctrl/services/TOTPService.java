package com.evalia.backend.ctrl.services;

import com.evalia.backend.models.Image;

public interface TOTPService {

	public String generateNewSecret();

	public Image generateQrCodeImageUri(String secret);

	public boolean isOtpValid(String secret, String code);

	public boolean isOtpNotValid(String secret, String code);

}
