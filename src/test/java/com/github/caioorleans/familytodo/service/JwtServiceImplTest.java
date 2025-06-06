package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.repository.UserRepository;
import com.github.caioorleans.familytodo.service.impl.JwtServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceImplTest {

    private JwtServiceImpl jwtService;
    private User testUser;

    @BeforeEach
    void setup() {
        var userRepository = Mockito.mock(UserRepository.class);
        jwtService = new JwtServiceImpl(userRepository);
        testUser = new User();
        testUser.setEmail("teste@exemplo.com");
    }

    @Test
    void deveGerarAccessTokenComSucesso() {
        String token = jwtService.generateAccessToken(testUser);
        assertNotNull(token);
        assertEquals(3, token.split("\\.").length); // Verifica formato JWT
    }

    @Test
    void deveGerarRefreshTokenComSucesso() {
        String token = jwtService.generateRefreshToken(testUser);
        assertNotNull(token);
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void deveExtrairEmailDoToken() {
        String token = jwtService.generateAccessToken(testUser);
        String emailExtraido = jwtService.getEmailFromToken(token);
        assertEquals(testUser.getEmail(), emailExtraido);
    }
}

