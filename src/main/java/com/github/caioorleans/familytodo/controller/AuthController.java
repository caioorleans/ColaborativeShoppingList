package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.mapper.UserMapper;
import com.github.caioorleans.familytodo.service.AuthService;
import jakarta.validation.Valid;
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
    public TokensDTO login(@RequestBody @Valid LoginDTO loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/createUserAndAuthenticate")
    public TokensDTO createUserAutneticate(@RequestBody @Valid UserCreateDTO userDTO) {
        return authService.createUsersAndAuthenticate(userMapper.toEntity(userDTO));
    }
}
