package com.github.caioorleans.familytodo.validation;

import com.github.caioorleans.familytodo.validation.decorator.ValidPassword;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidPasswordValidatorTest {

    private static Validator validator;

    static class PasswordTestDTO {
        @ValidPassword
        private final String password;

        public PasswordTestDTO(String password) {
            this.password = password;
        }
    }

    @BeforeAll
    static void setupValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    void assertInvalid(String password, String expectedMessage) {
        Set<ConstraintViolation<PasswordTestDTO>> violations = validator.validate(new PasswordTestDTO(password));
        assertFalse(violations.isEmpty(), "Deveria ter falhado para: " + password);
        assertEquals(expectedMessage, violations.iterator().next().getMessage());
    }

    @Test
    void testValidPassword() {
        Set<ConstraintViolation<PasswordTestDTO>> violations =
                validator.validate(new PasswordTestDTO("Aa1@567"));
        assertTrue(violations.isEmpty(), "Senha válida não deveria gerar erro");
    }

    @Test
    void testNullPassword() {
        assertInvalid(null, "A senha não pode ser nula");
    }

    @Test
    void testShortPassword() {
        assertInvalid("Aa1@", "A senha deve ter entre 6 e 14 caracteres");
    }

    @Test
    void testMissingUppercase() {
        assertInvalid("aa1@5678", "A senha deve conter ao menos uma letra maiúscula");
    }

    @Test
    void testMissingLowercase() {
        assertInvalid("AA1@5678", "A senha deve conter ao menos uma letra minúscula");
    }

    @Test
    void testMissingNumber() {
        assertInvalid("Aa@abcdef", "A senha deve conter ao menos um número");
    }

    @Test
    void testMissingSpecialChar() {
        assertInvalid("Aa15678", "A senha deve conter ao menos um caractere especial");
    }

    @Test
    void testContainsSpace() {
        assertInvalid("Aa1@ 567", "A senha não pode conter espaços");
    }

    @Test
    void testTooLongPassword() {
        assertInvalid("Aa1@56789012345", "A senha deve ter entre 6 e 14 caracteres");
    }
}

