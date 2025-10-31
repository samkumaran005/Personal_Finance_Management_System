package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.model.User;
import com.finance.personalfinancemanager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // static is same origin, but safe for testing
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // login: POST { "username":"demo", "password":"demo123" }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message","username and password required"));
        }
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        session.setAttribute("userId", user.getId());
                        return ResponseEntity.ok(Map.of("message","success"));
                    } else {
                        return ResponseEntity.status(401).body(Map.of("message","invalid credentials"));
                    }
                }).orElseGet(() -> ResponseEntity.status(401).body(Map.of("message","invalid credentials")));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message","logged out"));
    }
}
