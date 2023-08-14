package com.evalia.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evalia.backend.exceptions.InvalidPhoneException;
import com.evalia.backend.models.Entity;
import com.evalia.backend.models.Particular;
import com.evalia.backend.models.Professional;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import ignored.com.evalia.backend.payloads.request.SignupRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityUtils {

	private static final Logger logger = LoggerFactory.getLogger(EntityUtils.class);

	private static void populateEntity(Entity entity, SignupRequest request) {
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		if (!validatePhoneNumber(request.getIsoCode(), request.getPhone())) {
			throw InvalidPhoneException.build(request.getPhone());
		}
		entity.setPhone(request.getPhone());
		entity.setAddress(request.getAddress());
	}

	private static Professional buildProfessionEntity(SignupRequest request) {
		Professional professionalEntity = new Professional();
		populateEntity(professionalEntity, request);
		professionalEntity.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
		return professionalEntity;
	}

	private static Particular buildParticularEntity(SignupRequest request) {
		Particular particularEntity = new Particular();
		populateEntity(particularEntity, request);
		particularEntity.setSurname(request.getSurname());
		particularEntity.setBirthDate(request.getBirthDate());
		particularEntity.setAlternativeAddress(request.getAltAddress());
		return particularEntity;
	}

	public static boolean validatePhoneNumber(String isoCode, String phoneNumber) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber number = phoneUtil.parse(phoneNumber, isoCode);
			return phoneUtil.isValidNumberForRegion(number, isoCode);
		} catch (NumberParseException e) {
			logger.warn("The passed phone number cannot be parsed, please re-validate your input!", e.getMessage());
			return false;
		}
	}

	public static Entity convertSignupToEntity(SignupRequest request) {
		switch (request.getType()) {
		case PROFESSIONAL:
			return buildProfessionEntity(request);
		default:
			return buildParticularEntity(request);
		}
	}
}
