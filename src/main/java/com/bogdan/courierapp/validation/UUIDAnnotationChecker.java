package com.bogdan.courierapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;


public class UUIDAnnotationChecker implements ConstraintValidator<UUIDChecker, String> {

    private static final String TEMPLATE = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(el -> !el.isBlank())
                .map(uuid -> uuid.matches(TEMPLATE))
                .orElse(false);
    }
}
