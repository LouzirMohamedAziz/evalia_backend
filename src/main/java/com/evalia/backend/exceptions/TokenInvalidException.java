package com.evalia.backend.exceptions;

import com.evalia.backend.utils.ResourceUtils;

public class TokenInvalidException extends ApiException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6752115952648010202L;
	
	private static final String MSG_INVALIDTOKEN = "The token {0} is invalid";

	
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
