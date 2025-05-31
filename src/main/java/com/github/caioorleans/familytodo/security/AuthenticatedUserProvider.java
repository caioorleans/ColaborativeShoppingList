package com.github.caioorleans.familytodo.security;

import com.github.caioorleans.familytodo.exception.NotFoundException;
import com.github.caioorleans.familytodo.exception.UnauthorizedException;
import com.github.caioorleans.familytodo.model.User;
import com.github.caioorleans.familytodo.repository.UserRepository;
import com.github.caioorleans.familytodo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    private final UserService userService;

    public AuthenticatedUserProvider(UserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.isAuthenticated()) ? auth.getName() : null;
    }

    public User getUser() {
        var email = getEmail();
        try {
            return userService.findUserByEmail(email);
        } catch (NotFoundException e) {
            throw new UnauthorizedException("User not found");
        }
    }
}
