package com.pluralsight.springmvchibernate.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PSCodeConstraintValidator implements ConstraintValidator<PSCode, String> {

	public String codePrefix;

	@Override
	public void initialize(PSCode constraintAnnotation) {
		codePrefix = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid = false;
		if (value != null) {
			isValid = value.startsWith(codePrefix);
		}
		return isValid;
	}

}
