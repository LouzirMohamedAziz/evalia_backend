package com.evalia.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.evalia.backend.utils.ResourceUtils;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Internal resource not found!")
public class ResourceNotFoundException extends ApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8712264906553594113L;

	private static final String MSG_RESOURCENOTFOUND = "The requested resource {0}:{1} was not found!";

	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	
	public ResourceNotFoundException(String message, Exception e) {
		super(message, e);
	}

	
	public static ResourceNotFoundException build(String resourceType, String resourceId) {
		return new ResourceNotFoundException(
				ResourceUtils.buildMessage(MSG_RESOURCENOTFOUND, resourceType, resourceId));
	}
	
	
	public static ResourceNotFoundException build(String resourceType, String resourceId, Exception e) {
		return new ResourceNotFoundException(
				ResourceUtils.buildMessage(MSG_RESOURCENOTFOUND, resourceType, resourceId), e);
	}
}
