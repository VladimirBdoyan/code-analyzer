package com.example.masterservice.security.constants;

public final class JWTConstants {

    private JWTConstants() {
    }

    public static final String SECRET = "My secret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
