package com.github.caioorleans.familytodo.controller;

import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.mapper.UserMapper;
import com.github.caioorleans.familytodo.service.JwtService;
import com.github.caioorleans.familytodo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String addUser(@RequestBody @Valid UserCreateDTO userDTO) {
        var user = userService.createUser(userMapper.toEntity(userDTO));
        return jwtService.generateToken(user);
    }
}
