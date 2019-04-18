package com.dane.peeper.domain.security;

public class TokenConstants {
    static final String SECRET = "THIS_IS_A_SUPER_SECRET_KEY";
    static final long EXPIRATION_TIME = 108_000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/v1/users";
}
