package com.trkdmrl.readingisgood.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueEmailValidator.class })
public @interface UniqueEmail {

    String message() default "This email is in use";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
