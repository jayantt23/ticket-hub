package com.jayant.user_service.controller;

import com.jayant.user_service.dto.AuthenticationRequest;
import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;
import com.jayant.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(
                authService.register(registerRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(
                authService.authenticate(authenticationRequest),
                HttpStatus.OK
        );
    }
}
