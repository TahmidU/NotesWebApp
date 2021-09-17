package com.tahmidu.notes_web_app.constant;

public class SecurityConstants {

    public static final String ACCESS_SECRET = "very_secret"; // This is usually put in an env var.
    public static final String REFRESH_SECRET = "another_secret"; // This is usually put in an env var.
    public static final long ACCESS_JWT_EXPIRATION_TIME = 1000L*60L*5L; // 5 minutes
    public static final long REFRESH_JWT_EXPIRATION_TIME = 1000L*60L*60L*24L*240L; // 240 days
    public static final String JSON_ACCESS = "accessToken";
    public static final String JSON_REFRESH = "refreshToken";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_JWT_AUTHORIZATION = "Authorization";
    public static final String HEADER_AUTH_FAIL = "Failed";

}
