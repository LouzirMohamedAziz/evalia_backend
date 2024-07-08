package com.evalia.backend.exceptions;

import com.evalia.backend.util.ResourceUtils;

public class ValueConversionException extends ApiException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5175298908672386499L;
	
	private static final String MSG_INVALIDTOKEN = "Failed to convert {0} to {1}";

	
    public ValueConversionException(String message) {
        super(message);
        
    }


    public ValueConversionException(String message, Exception e) {
        super(message,e);
    }

    
    public static ValueConversionException build(String value, String type) {
		return new ValueConversionException(
				ResourceUtils.buildMessage(MSG_INVALIDTOKEN, value, type));
	}
	
	
	public static ValueConversionException build(String value, String type, Exception e) {
		return new ValueConversionException(
				ResourceUtils.buildMessage(MSG_INVALIDTOKEN, value, type), e);
	}
}
