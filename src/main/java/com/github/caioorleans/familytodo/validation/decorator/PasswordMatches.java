package com.github.caioorleans.familytodo.validation.decorator;

import com.github.caioorleans.familytodo.validation.validator.PasswordMatchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "A senha e a confirmação de senha não coincidem";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
