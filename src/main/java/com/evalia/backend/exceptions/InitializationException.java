package com.evalia.backend.exceptions;

import com.evalia.backend.utils.ResourceUtils;


public class InitializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5145742752891759347L;
	
	private static final String MSG_PRELOADING = "Initialization of {0} failed!";
	
	
	public InitializationException(String message) {
		super(message);
	}
	
	
	public InitializationException(String message, Exception e) {
		super(message, e);
	}
	
	
	public static InitializationException build(String entityName) {
		return new InitializationException(
				ResourceUtils.buildMessage(MSG_PRELOADING, entityName));
	}
	
	
	public static InitializationException build(String entityName, Exception e) {
		return new InitializationException(
				ResourceUtils.buildMessage(MSG_PRELOADING, entityName), e);
	}
}
