package com.example.storeelectronic.demo.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValidated implements ConstraintValidator<ImageNameValid, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null || s.isBlank()) {
            return false;
        }


        String lowerCase = s.toLowerCase();
        return lowerCase.endsWith(".png") || lowerCase.endsWith(".jpg")
                || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".gif");
    }
}
