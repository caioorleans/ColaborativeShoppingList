package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.User;

public interface JwtService {
    public String generateToken(User user);
}
