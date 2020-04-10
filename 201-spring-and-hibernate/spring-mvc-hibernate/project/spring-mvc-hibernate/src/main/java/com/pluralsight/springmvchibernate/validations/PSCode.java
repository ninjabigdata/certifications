package com.pluralsight.springmvchibernate.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PSCodeConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PSCode {
	
	String value() default "PS";
	String message() default "PS code should start with PS";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
