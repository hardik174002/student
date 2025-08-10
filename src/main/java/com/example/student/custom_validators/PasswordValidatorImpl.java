package com.example.student.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator, String>{

    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        // optional: can access annotation values here
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return PASSWORD.matcher(value).matches();
    }

}
