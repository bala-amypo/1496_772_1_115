package com.example.demo.controller;

import com.example.demo.dto.JWTResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtUtil jwt;

    public AuthController(UserService service, JwtUtil jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public JWTResponse register(@RequestBody RegisterRequest req) {
        User u = new User(null, req.fullName, req.email, req.password, null);
        User saved = service.registerUser(u);

        String token = jwt.generateToken(
                saved.getId(),
                saved.getEmail(),
                saved.getRole()
        );

        return new JWTResponse(
                token,
                saved.getId(),
                saved.getEmail(),
                saved.getRole()
        );
    }

    @PostMapping("/login")
    public JWTResponse login(@RequestBody LoginRequest req) {
        User u = service.findByEmail(req.email);

        String token = jwt.generateToken(
                u.getId(),
                u.getEmail(),
                u.getRole()
        );

        return new JWTResponse(
                token,
                u.getId(),
                u.getEmail(),
                u.getRole()
        );
    }
}
