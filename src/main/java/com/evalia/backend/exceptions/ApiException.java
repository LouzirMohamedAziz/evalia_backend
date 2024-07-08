package com.evalia.backend.exceptions;

public abstract class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2373148251706980323L;

	
	public ApiException(String message) {
		super(message);
	}
	
	
	public ApiException(String message, Exception e) {
		super(message, e);
	}
}
