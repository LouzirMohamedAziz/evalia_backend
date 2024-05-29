package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class UserNotFoundException extends ApiException{
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, Exception e) {
        super(message,e);
    }

    private static final String MSG_USERNOTFOUND = "The user with email address {0} not found";

    public static UserNotFoundException build(String token) {
		return new UserNotFoundException(
				ResourceUtils.buildMessage(MSG_USERNOTFOUND, token));
	}
	
	
	public static UserNotFoundException build(String token, Exception e) {
		return new UserNotFoundException(
				ResourceUtils.buildMessage(MSG_USERNOTFOUND, token), e);
	}
}
