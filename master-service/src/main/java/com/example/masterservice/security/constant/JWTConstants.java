package com.example.masterservice.security.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class JWTConstants {
    public static final String SECRET = "My secret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
