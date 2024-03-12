package com.bogdan.courierapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UUIDAnnotationChecker.class})
public @interface UUIDChecker {
    String message() default "Its not UUID format!!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
