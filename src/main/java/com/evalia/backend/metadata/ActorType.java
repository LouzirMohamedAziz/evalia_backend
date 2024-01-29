package com.evalia.backend.metadata;

/**
 * @author Hamdi Jandoubi
 *
 */
public enum ActorType implements Enumeration{

	/**
	 * A privately owned enterprise
	 */
	PRIVATE("Private"),
	
	/**
	 * A state-owned enterprise
	 */
	PUBLIC("Public"),

	/**
	 * A state-owned enterprise
	 */
	PARTNERSHIP("Partnership"),
	
	/**
	 * A normal citizen
	 */
	CIVIL("Civil");
	
	
	private final String alias;
	
	
	
	private ActorType(String alias) {
		this.alias = alias;
	}

	
	@Override
	public String getAlias() {
		return this.alias;
	}
}
