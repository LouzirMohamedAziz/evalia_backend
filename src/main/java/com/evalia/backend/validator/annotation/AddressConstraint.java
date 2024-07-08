package com.evalia.backend.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.evalia.backend.validator.AddressValidator;

@Documented
@Constraint(validatedBy = AddressValidator.class)
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface AddressConstraint {

	String message() default "Provided Address instance is not valid!";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
