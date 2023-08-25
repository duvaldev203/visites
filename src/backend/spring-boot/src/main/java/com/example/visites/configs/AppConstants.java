package com.example.visites.configs;

public class AppConstants {
    public static final String[] PUBLIC_GET_URLS = { "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" };
    public static final String[] PUBLIC_POST_URLS = { "/register/**", "/login", "/register" };
    public static final Long ADMIN_ID = 101L;
    public static final Long USER_ID = 102L;

    public static final String CHARACTERS_ALLOWED_FOR_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@-*+_?";

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=\\-_!])(?=\\S+$).{8,}$";
}
