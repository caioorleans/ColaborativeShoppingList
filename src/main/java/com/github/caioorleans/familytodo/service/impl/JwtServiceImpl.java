package com.github.caioorleans.familytodo.service.impl;

import com.github.caioorleans.familytodo.exception.UnauthorizedException;
import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.repository.UserRepository;
import com.github.caioorleans.familytodo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtServiceImpl implements JwtService, UserDetailsService {

    @Value("${security.access-token-key}")
    private String ACCESS_TOKEN_SECRET_KEY;

    @Value("${security.access-token-expiration-time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${security.refresh-token-key}")
    private String REFRESH_TOKEN_SECRET_KEY;

    @Value("${security.refresh-token-expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private final UserRepository userRepository;

    public JwtServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String generateAccessToken(User user) {
        return generateToken(user.getEmail(), ACCESS_TOKEN_SECRET_KEY, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    @Override
    public String generateRefreshToken(User user) {
        return generateToken(user.getEmail(), REFRESH_TOKEN_SECRET_KEY, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    @Override
    public String getEmailFromToken(String token) {
        return extractClaims(token, ACCESS_TOKEN_SECRET_KEY).getSubject();
    }

    @Override
    public Claims extractAccessToken(String token) {
        try{
            return extractClaims(token, ACCESS_TOKEN_SECRET_KEY);
        }catch (JwtException e){
            throw new UnauthorizedException("Invalid token");
        }
    }

    @Override
    public Claims extractRefreshToken(String token) {
        try{
            return extractClaims(token, REFRESH_TOKEN_SECRET_KEY);
        }catch (JwtException e){
            throw new UnauthorizedException("Invalid token");
        }
    }

    private Claims extractClaims(String token, String key) {
        var secretKey = Keys.hmacShaKeyFor(key.getBytes());
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(String email, String key, long expirationTimeInSeconds) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTimeInSeconds * 1000);

        var secretKey = Keys.hmacShaKeyFor(key.getBytes());

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
