package com.jayant.user_service.controller;

import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;
import com.jayant.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request,
            @RequestHeader("X-User-Role") String role
            ) {
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Only Admins can create other Admins.");
        }

        return ResponseEntity.ok(authService.registerAdmin(request));
    }
}
