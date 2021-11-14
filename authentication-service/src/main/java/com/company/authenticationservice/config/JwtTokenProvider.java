package com.example.security.config;

import com.auth0.jwt.JWT;
import com.example.security.model.User;
import com.example.security.model.enums.Role;
import com.example.security.security.JWTProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JwtTokenProvider {

    public String jwtTockenCreator(User user){
        return   JWTProperties.TOKEN_PREFIX+
                 JWT.create()
                .withClaim("roles", user.getAuthorities().toString())
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTProperties.EXPIRATION_TIME))
                .sign(HMAC512(JWTProperties.SECRET.getBytes()));
    }
}
