package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.dto.LoginDTO;
import com.github.caioorleans.familytodo.dto.TokensDTO;

public interface AuthService {

    TokensDTO login(LoginDTO loginDTO);
}
