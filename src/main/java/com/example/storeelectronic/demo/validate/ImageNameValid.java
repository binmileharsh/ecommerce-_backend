package com.example.storeelectronic.demo.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageNameValidated.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })  // Where this annotation can be applied
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageNameValid {

    String message() default "should not be blank also must have extension like.jpeg or png or gif";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

