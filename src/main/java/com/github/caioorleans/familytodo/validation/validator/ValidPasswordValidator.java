package com.github.caioorleans.familytodo.validation.validator;

import com.github.caioorleans.familytodo.validation.decorator.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            setMessage(context, "A senha não pode ser nula");
            return false;
        }

        if (password.length() < 6 || password.length() > 14) {
            setMessage(context, "A senha deve ter entre 6 e 14 caracteres");
            return false;
        }

        if (password.contains(" ")) {
            setMessage(context, "A senha não pode conter espaços");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            setMessage(context, "A senha deve conter ao menos uma letra maiúscula");
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            setMessage(context, "A senha deve conter ao menos uma letra minúscula");
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            setMessage(context, "A senha deve conter ao menos um número");
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()\\-_=+{}\\[\\]:;\"'<>,.?/\\\\|].*")) {
            setMessage(context, "A senha deve conter ao menos um caractere especial");
            return false;
        }

        return true;
    }

    private void setMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}

