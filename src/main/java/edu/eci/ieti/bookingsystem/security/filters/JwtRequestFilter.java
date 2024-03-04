package edu.eci.ieti.bookingsystem.security.filters;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.eci.ieti.bookingsystem.controller.auth.TokenAuthentication;
import edu.eci.ieti.bookingsystem.exception.TokenExpiredException;
import edu.eci.ieti.bookingsystem.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtRequestFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull  FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Claims claims = jwtUtils.extractAndVerifyClaims(token);
                String userName = claims.getSubject();
                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    TokenAuthentication tokenAuthentication = new TokenAuthentication(token, userName);
                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                }
            }
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token expired");
        }
        filterChain.doFilter(request, response);
    }

}
