package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.User;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String getEmailFromToken(String token);
    Claims extractAccessToken(String token);
    Claims extractRefreshToken(String token);
}
