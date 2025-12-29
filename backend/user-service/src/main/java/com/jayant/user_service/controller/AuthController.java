package com.jayant.user_service.controller;

import com.jayant.user_service.dto.AuthenticationRequest;
import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;
import com.jayant.user_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth API", description = "Endpoints for creating and authorising users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a User", description = "Creates a new user and returns a Jwt Token")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(
                authService.register(registerRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a User", description = "Authenticates a User and returns a Jwt Token")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(
                authService.authenticate(authenticationRequest),
                HttpStatus.OK
        );
    }
}
