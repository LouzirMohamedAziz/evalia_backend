package com.evalia.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.evalia.backend.utils.ResourceUtils;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Resource already exists!")
public class ResourceAlreadyExistsException extends ApiException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5252515359643773342L;
	
	private static final String MSG_RESOURCEEXISTS = "The requested resource {0}:{1} is already saved!";
	
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
	
	public ResourceAlreadyExistsException(String message, Exception e) {
		super(message, e);
	}
	
	
	public static ResourceAlreadyExistsException build(String resourceType, String resourceId) {
		return new ResourceAlreadyExistsException(
				ResourceUtils.buildMessage(MSG_RESOURCEEXISTS, resourceType, resourceId));
	}
	
	public static ResourceAlreadyExistsException build(String resourceType, String resourceId, Exception e) {
		return new ResourceAlreadyExistsException(
				ResourceUtils.buildMessage(MSG_RESOURCEEXISTS, resourceType, resourceId), e);
	}

}
