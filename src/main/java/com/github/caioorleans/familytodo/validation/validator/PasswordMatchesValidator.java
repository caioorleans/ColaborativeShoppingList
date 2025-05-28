package com.github.caioorleans.familytodo.validation.validator;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.validation.decorator.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        // Não usamos nada aqui, mas poderia inicializar dados da anotação se precisasse
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof UserCreateDTO user) {
            if (user.getPassword() == null || user.getPasswordConfirmation() == null) {
                return false; // Se algum dos dois campos for nulo, falha a validação
            }
            return user.getPassword().equals(user.getPasswordConfirmation());
        }
        return false;
    }
}
