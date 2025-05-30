package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.exception.UnauthorizedException;
import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.service.AuthService;
import com.github.caioorleans.familytodo.service.JwtService;
import com.github.caioorleans.familytodo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            JwtService jwtService, UserService userService,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokensDTO login(LoginDTO loginDTO) {
        var user = userService.findUserByEmail(loginDTO.getEmail());
        passwordMatches(loginDTO, user);
        return new TokensDTO(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }

    private void passwordMatches(LoginDTO loginDTO, User user) {
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong password");
        }
    }
}
