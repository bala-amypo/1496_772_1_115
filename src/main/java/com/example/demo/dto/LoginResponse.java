package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private String token;
    private String message;

    // ✅ Used for LOGIN (token visible)
    public LoginResponse(String token) {
        this.token = token;
    }

    // ✅ Used for REGISTER (message only)
    public LoginResponse(String message, boolean isRegister) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
