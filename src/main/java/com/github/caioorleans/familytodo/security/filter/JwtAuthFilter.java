package com.github.caioorleans.familytodo.security.filter;

import com.github.caioorleans.familytodo.exception.UnauthorizedException;
import com.github.caioorleans.familytodo.security.SecurityProperties;
import com.github.caioorleans.familytodo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userService;
    private final SecurityProperties securityProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userService, SecurityProperties securityProperties) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                sendUnauthorizedResponse(response, "You have to be authorized to access this resource");
                return;
            }
            var jwt = authHeader.substring(7);
            var claims = jwtService.extractAccessToken(jwt);
            if(claims.getExpiration().before(new Date())) {
                sendUnauthorizedResponse(response, "Token is expired.");
                return;
            }
            var email = jwtService.getEmailFromToken(jwt);
            var userDetails = userService.loadUserByUsername(email);
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            sendUnauthorizedResponse(response, e.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return securityProperties.getPublicPaths().stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
                String.format("{\"status\": 401, \"message\": \"%s\"}", message)
        );
        response.getWriter().flush();
    }
}
