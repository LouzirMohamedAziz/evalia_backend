package com.evalia.backend.metadata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hamdi Jandoubi
 *
 */
public enum Role implements Enumeration{
	
	/**
	 * The role given to normal citizen.
	 */
	ROLE_CIVIL("Civil"),
	
	/**
	 * The role given to companies.
	 */
	ROLE_PROFESSIONAL("Professional"),
	
	/**
	 * The role given to administrators of the system.
	 */
    ROLE_ADMIN("Admin");
	
	
	private final String alias;
	
	
	private Role(String alias) {
		this.alias = alias;
	}
	

	@Override
	public String getAlias() {
		return this.alias;
	}
}
