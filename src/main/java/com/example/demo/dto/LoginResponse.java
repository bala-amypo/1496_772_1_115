package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginResponse {

    @JsonIgnore
    private String token;

    private String message;

    public LoginResponse(String token) {
        this.token = token;
        this.message = "Registration successful";
    }

    public String getMessage() {
        return message;
    }
}
