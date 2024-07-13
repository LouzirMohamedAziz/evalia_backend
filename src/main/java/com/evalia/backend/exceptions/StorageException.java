package com.evalia.backend.exceptions;

public class StorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460007553523413033L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Exception e) {
		super(message, e);
	}
}
