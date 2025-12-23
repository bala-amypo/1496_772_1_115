package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                request.getPassword(),
                null
        );

        User savedUser = userService.registerUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", savedUser.getId());
        response.put("email", savedUser.getEmail());

        return ResponseEntity.ok(response);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        // 🔐 Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // ✅ Return JWT DTO (important for tests)
        JwtResponse jwtResponse = new JwtResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(jwtResponse);
    }
}
