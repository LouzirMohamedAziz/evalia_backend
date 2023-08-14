package com.evalia.backend.util;

import com.evalia.backend.models.Administrative;

import ignored.com.evalia.backend.payloads.request.SignupRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdministrativeUtils {
	
	//private static final Logger logger = LoggerFactory.getLogger(AdministrativeUtils.class);
	
	
	public static Administrative convertSignupToAdministrative(SignupRequest request) {
		return new Administrative(request.getName(),
				request.getSurname(), request.getEmail());
	}
}
