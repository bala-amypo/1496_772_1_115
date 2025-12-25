package com.example.demo.dto;

public class JWTResponse {

    private String token;
    private Long userId;
    private String email;
    private String role;

    public JWTResponse() {}

    public JWTResponse(String token, Long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
