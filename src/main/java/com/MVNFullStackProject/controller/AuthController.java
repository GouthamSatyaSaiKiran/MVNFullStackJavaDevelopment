package com.MVNFullStackProject.controller;

import com.MVNFullStackProject.dto.ResetRequestDTO;
import com.MVNFullStackProject.dto.UserDTO;
import com.MVNFullStackProject.entity.User;
import com.MVNFullStackProject.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO dto) {
        Optional<User> loginResult = service.login(dto);
        if (loginResult.isPresent()) {
            User user = loginResult.get();
            String welcomeMessage = "Login successful, welcome " + user.getName() + "!";
            return ResponseEntity.ok(welcomeMessage);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody UserDTO dto) {
        String email = dto.getEmail();
        String token = service.initiatePasswordReset(email);
        return token != null ? ResponseEntity.ok("Reset token: " + token)
                             : ResponseEntity.badRequest().body("Email not found");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody ResetRequestDTO request) {
        boolean result = service.resetPassword(request.getToken(), request.getNewPassword());
        return result ? ResponseEntity.ok("Password reset successfully")
                      : ResponseEntity.badRequest().body("Invalid token");
    }
}
