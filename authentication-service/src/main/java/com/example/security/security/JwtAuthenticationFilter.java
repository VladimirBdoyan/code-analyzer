package com.example.security.security;

import com.auth0.jwt.JWT;
import com.example.security.config.JwtTokenProvider;
import com.example.security.dto.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //Trigger when we issue POST request to /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Grab credentials and map them to login request
        UserRequest credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token (not for user yet, but only for app internal use)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        // Grab userDetails
        UserDetails user = (UserDetails) authResult.getPrincipal();

        String token = JWTProperties.TOKEN_PREFIX +
                JWT.create()
                        .withClaim("roles", user.getAuthorities().toString())
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTProperties.EXPIRATION_TIME))
                        .sign(HMAC512(JWTProperties.SECRET.getBytes()));
        // Add token in response
        response.addHeader(JWTProperties.HEADER_STRING, token);
    }
}
