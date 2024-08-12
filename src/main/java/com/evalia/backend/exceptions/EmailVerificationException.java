package com.evalia.backend.exceptions;

import com.evalia.backend.utils.ResourceUtils;

public class EmailVerificationException extends ApiException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5379883383769106694L;
	
	private static final String MSG_EMAIL_VERIFICATION_ERROR = "The email {0} is already verified! ";

	
    public EmailVerificationException(String message) {
		super(message);
	}
	
    
	
	public EmailVerificationException(String message, Exception e) {
		super(message, e);
	}

	
	public static EmailVerificationException build(String email) {
		return new EmailVerificationException(
				ResourceUtils.buildMessage(MSG_EMAIL_VERIFICATION_ERROR,email));
	}
	
	
	public static EmailVerificationException build( String email, Exception e) {
		return new EmailVerificationException(
				ResourceUtils.buildMessage(MSG_EMAIL_VERIFICATION_ERROR,email), e);
	}

}
