package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class TokenExpiredException extends ApiException{

    private static final String MSG_TOKENEXPIRED = "The token {0} is expired";

    public TokenExpiredException(String message) {
        super(message);
        
    }


    public TokenExpiredException(String message, Exception e) {
        super(message,e);
    }

    public static TokenExpiredException build(String token) {
		return new TokenExpiredException(
				ResourceUtils.buildMessage(MSG_TOKENEXPIRED, token));
	}
	
	
	public static TokenExpiredException build(String token, Exception e) {
		return new TokenExpiredException(
				ResourceUtils.buildMessage(MSG_TOKENEXPIRED, token), e);
	}

}
