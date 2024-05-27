package com.evalia.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
	
	public static final String PIPE = "|";
	
	public static final String COUNTRY = "country";
	public static final String GOVERNORATE = "governorate";
	public static final String DELEGATION = "delegation";
    
    //prefixes
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String PERF_PREFIX = "PERF_";
    
    //Error messages 
    public static final String INVALID_ADDRESS = "Invalid Address";
    public static final String INVALID_PASSWORD = "Invalid Password";
    public static final String INVALID_COUNTRY = "Invalid Country";
    public static final String INVALID_ID = "Id {0} in type {0} is malformed!";
}