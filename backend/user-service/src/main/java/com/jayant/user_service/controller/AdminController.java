package com.jayant.user_service.controller;

import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;
import com.jayant.user_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
@Tag(name = "Auth API", description = "Endpoints for creating admins")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Create an Admin", description = "Creates a new admin and returns a Jwt Token")
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
