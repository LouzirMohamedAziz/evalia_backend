package com.evalia.backend.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.evalia.backend.util.ResourceUtils;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested resource not found!")
public class ResourceNotFoundException extends IOException {

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
