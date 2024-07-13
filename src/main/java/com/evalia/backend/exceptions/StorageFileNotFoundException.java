package com.evalia.backend.exceptions;

public class StorageFileNotFoundException extends StorageException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6115279071711663588L;

	
	public StorageFileNotFoundException(String message) {
		super(message);
	}
	
	public StorageFileNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
