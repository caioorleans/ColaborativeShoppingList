package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public TokensDTO login(@RequestBody LoginDTO loginDto) {
        return authService.login(loginDto);
    }
}
