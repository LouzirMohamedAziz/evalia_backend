package com.evalia.backend.utils.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.evalia.backend.models.Address;
import com.evalia.backend.models.Country;
import com.evalia.backend.models.Delegation;
import com.evalia.backend.models.Governorate;
import com.evalia.backend.utils.validator.annotation.AddressConstraint;

public class AddressValidator implements ConstraintValidator<AddressConstraint, Address> {

	
	@Override
	public boolean isValid(Address value, ConstraintValidatorContext context) {
		
		Country country = value.getCountry();
		Governorate governorate = value.getGovernorate();
		Delegation delegation = value.getDelegation();
		
		boolean isValid = true;
		
		isValid &= country.getGovernorates().contains(governorate);
		
		if(Objects.nonNull(delegation)) {
			isValid &= governorate.getDelegations().contains(delegation);
		}
		
		return isValid;
	}
}
