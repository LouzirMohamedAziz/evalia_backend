package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class TokenInvalidException extends ApiException{

    private static final String MSG_INVALIDTOKEN = "The token {0} is expired";

    public TokenInvalidException(String message) {
        super(message);
        
    }


    public TokenInvalidException(String message, Exception e) {
        super(message,e);
    }

    public static TokenInvalidException build(String token) {
		return new TokenInvalidException(
				ResourceUtils.buildMessage(MSG_INVALIDTOKEN, token));
	}
	
	
	public static TokenInvalidException build(String token, Exception e) {
		return new TokenInvalidException(
				ResourceUtils.buildMessage(MSG_INVALIDTOKEN, token), e);
	}

}
