package com.evalia.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
	
	//Characters
	public static final String PIPE = "|";
	
	//Regex patterns
	public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&+=]).{8,20}$";
	
	//Properties
	public static final String COUNTRY = "country";
	public static final String GOVERNORATE = "governorate";
	public static final String DELEGATION = "delegation";
    
    //prefixes
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String PERF_PREFIX = "PERF_";
    
    //Error messages 
    public static final String INVALID_ADDRESS = "Invalid Address";
    public static final String INVALID_PASSWORD = "Password does not meet the criteria!\n" + 
			"The password must: \n" +
			"\t*Contain at least one uppercase character,\n" +
			"\t*Contain at least one lowercase character,\n" +
			"\t*Contain at least one digit,\n" +
			"\t*Contain at least one special character (@, #, $, %, &, +, =),\n" +
			"\t*Be 8 to 20 characters long.";
    public static final String INVALID_COUNTRY = "Invalid Country";
    public static final String INVALID_ID = "Id {0} in type {0} is malformed!";
	public static final String RESET_TOKEN_MAIL_SUBJECT = "Password reset token";
}