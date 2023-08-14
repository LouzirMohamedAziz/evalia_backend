package com.evalia.backend.exceptions;

import java.io.FileNotFoundException;

import com.evalia.backend.util.ResourceUtils;

public class ResourceNotFoundException extends FileNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8712264906553594113L;

	private static final String MSG_FILENOTFOUND = "The file {0} was not found on the classpath!";

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Exception e) {
		super(message);
	}

	public static ResourceNotFoundException build(String resourceName) {
		return new ResourceNotFoundException(
				ResourceUtils.buildMessage(MSG_FILENOTFOUND, resourceName));
	}
	
	public static ResourceNotFoundException build(String resourceName, Exception e) {
		return new ResourceNotFoundException(
				ResourceUtils.buildMessage(MSG_FILENOTFOUND, resourceName), e);
	}
}
