package com.evalia.backend.exceptions;

import com.evalia.backend.utils.ResourceUtils;

public class InvalidEvaluaterException extends ApiException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5175298908672386499L;
	
	private static final String MSG_INVALIDEVALUATOR = "Evaluator of type {0} is not allowed for evaluation!";

	
    public InvalidEvaluaterException(String message) {
        super(message);
        
    }


    public InvalidEvaluaterException(String message, Exception e) {
        super(message,e);
    }

    
    public static InvalidEvaluaterException build(String type) {
		return new InvalidEvaluaterException(
				ResourceUtils.buildMessage(MSG_INVALIDEVALUATOR, type));
	}
	
	
	public static InvalidEvaluaterException build(String type, Exception e) {
		return new InvalidEvaluaterException(
				ResourceUtils.buildMessage(MSG_INVALIDEVALUATOR, type), e);
	}
}
