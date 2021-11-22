package com.example.masterservice.security;

public final class JWTProperties {
    public static final String SECRET = "My secret";
    public static final int EXPIRATION_TIME = 900000;//15 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
