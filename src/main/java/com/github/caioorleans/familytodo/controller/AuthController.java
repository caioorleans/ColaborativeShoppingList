package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.mapper.UserMapper;
import com.github.caioorleans.familytodo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<TokensDTO> login(@RequestBody @Valid LoginDTO loginDto) {
        var tokensDto = authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tokensDto);
    }

    @PostMapping("/createUserAndAuthenticate")
    public ResponseEntity<TokensDTO> createUserAutneticate(@RequestBody @Valid UserCreateDTO userDTO) {
        var tokens = authService.createUsersAndAuthenticate(userMapper.toEntity(userDTO));
        return ResponseEntity.ok(tokens);
    }
}
