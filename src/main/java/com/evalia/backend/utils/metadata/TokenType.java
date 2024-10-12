package com.evalia.backend.utils.metadata;
/**
 * @author Mohamed Ben Hamouda
 *
 */
public enum TokenType implements Enumeration{
    
    PASSWORD_TOKEN("PASSWORD_TOKEN"),
    EMAIL_TOKEN("EMAIL_TOKEN"),
    TOTP_TOKEN("TOTP_TOKEN");

    private final String alias;
	
	
	private TokenType(String alias) {
		this.alias = alias;
	}
	

	@Override
	public String getAlias() {
		return this.alias;
	}
}
