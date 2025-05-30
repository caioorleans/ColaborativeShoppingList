package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.mapper.UserMapper;
import com.github.caioorleans.familytodo.service.JwtService;
import com.github.caioorleans.familytodo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public UserController(
            UserService userService,
            JwtService jwtService,
            UserMapper userMapper
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public TokensDTO addUser(@RequestBody @Valid UserCreateDTO userDTO) {
        var user = userService.createUser(userMapper.toEntity(userDTO));
        return new TokensDTO(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }

    @GetMapping
    public String teste(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return "Logged in as: " + username;
    }
}
