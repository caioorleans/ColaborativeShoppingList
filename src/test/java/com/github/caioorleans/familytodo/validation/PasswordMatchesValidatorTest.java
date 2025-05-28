package com.github.caioorleans.familytodo.validation;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordMatchesValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenPasswordsMatch_thenValidationPasses() {
        UserCreateDTO dto = new UserCreateDTO("Caio","email@example.com","Senha123!", "Senha123!");
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenPasswordsDoNotMatch_thenValidationFails() {
        UserCreateDTO dto = new UserCreateDTO("Caio","email@example.com","Senha123!", "SenhaDiferente");
        Set<ConstraintViolation<UserCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        String message = violations.iterator().next().getMessage();
        assertEquals("A senha e a confirmação de senha não coincidem", message);
    }

}
