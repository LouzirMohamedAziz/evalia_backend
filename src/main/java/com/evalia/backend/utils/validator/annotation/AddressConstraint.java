package com.evalia.backend.utils.validator.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.evalia.backend.utils.validator.AddressValidator;

@Documented
@Constraint(validatedBy = AddressValidator.class)
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface AddressConstraint {

	String message() default "Provided Address instance is not valid!";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
