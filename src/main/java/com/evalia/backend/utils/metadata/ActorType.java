package com.evalia.backend.utils.metadata;

/**
 * @author Hamdi Jandoubi
 *
 */
public enum ActorType implements Enumeration{

//	/**
//	 * A privately owned enterprise
//	 */
//	PRIVATE("Private"),
//	
//	/**
//	 * A state-owned enterprise
//	 */
//	PUBLIC("Public"),
//
//	/**
//	 * A state-owned enterprise
//	 */
//	PARTNERSHIP("Partnership"),
	
	/**
	 * A normal citizen
	 */
	CIVIL("Personal"),
	
	/**
	 * A professional Enterprise
	 */
	PROFESSIONAL("Professional"),

	/**
	 * A not verified legalEntities
	 */
	LEGAL_ENTITY("LegalEntity");
	
	
	private final String alias;
	
	
	
	private ActorType(String alias) {
		this.alias = alias;
	}

	
	@Override
	public String getAlias() {
		return this.alias;
	}
}
