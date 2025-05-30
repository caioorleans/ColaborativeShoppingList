package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;
import com.github.caioorleans.familytodo.dto.UserCreateDTO;
import com.github.caioorleans.familytodo.model.User;

public interface AuthService {

    TokensDTO login(LoginDTO loginDTO);
    TokensDTO createUsersAndAuthenticate(User user);
}
