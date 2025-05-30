package com.github.caioorleans.familytodo.service;

import com.github.caioorleans.familytodo.model.User;

public interface JwtService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String getEmailFromToken(String token);
    boolean isAccessTokenValid(String token);
    boolean isRefreshTokenValid(String token);
}
