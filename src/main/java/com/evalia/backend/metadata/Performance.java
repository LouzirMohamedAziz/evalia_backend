package com.evalia.backend.metadata;

public enum Performance implements Enumeration{
    
	/**
	 * The Economical Performance.
	 */
	PERFORMANCE_ECONOMICAL("Economical"),
	
	/**
	 * The Social Performance.
	 */
	PERFORMANCE_SOCIAL("Social"),

	/**
	 * The Social Performance.
	 */
	PERFORMANCE_ENVIRONMENTAL("Environmental");

	
	
	private final String alias;
	
	private Performance(String alias) {
		this.alias = alias;
	}

	@Override
	public String getAlias() {
		return this.alias;
	}

    
}