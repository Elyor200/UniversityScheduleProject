package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.login.LoginRequestDTO;
import com.universityscheduleproject.dto.login.LoginResponseDTO;
import com.universityscheduleproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO login = authService.login(loginRequestDTO);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {
        return authService.getCurrentUser(authentication);
    }

}
