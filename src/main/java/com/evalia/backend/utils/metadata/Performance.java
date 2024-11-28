package com.evalia.backend.utils.metadata;

public enum Performance implements Enumeration{
    
	/**
	 * The Economic Performance.
	 */
	Economic("Economic"),
	
	/**
	 * The Social Performance.
	 */
	SOCIAL("Social"),

	/**
	 * The Social Performance.
	 */
	ENVIRONMENTAL("Environmental");

	
	
	private final String alias;
	
	private Performance(String alias) {
		this.alias = alias;
	}

	@Override
	public String getAlias() {
		return this.alias;
	}
}