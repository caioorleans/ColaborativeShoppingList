package com.github.caioorleans.familytodo.validation.decorator;

import com.github.caioorleans.familytodo.validation.validator.ValidPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Senha inválida"; // Substituído pelo validador com mensagens específicas
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
