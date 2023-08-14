package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class RoleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6953958444244903440L;
	
	private static final String MSG_TEMPLATE = "Role '{0}' was not preloaded! "
			+ "Please contact a support member to address this issue.";
	
	public RoleNotFoundException(String message) {
		super(message);
	}

	public static RoleNotFoundException build(String roleName) {
		return new RoleNotFoundException(
				ResourceUtils.buildMessage(MSG_TEMPLATE, roleName));
	}
}
