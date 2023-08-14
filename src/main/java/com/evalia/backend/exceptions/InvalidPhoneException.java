package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class InvalidPhoneException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940820823797873704L;
	
	private static final String MSG_TEMPLATE = "Phone number {0} is not valid! please verify your input and retry.";
	
	public InvalidPhoneException(String message) {
		super(message);
	}

	public static InvalidPhoneException build(String phoneNumber) {
		return new InvalidPhoneException(
				ResourceUtils.buildMessage(MSG_TEMPLATE, phoneNumber));
	}
}
