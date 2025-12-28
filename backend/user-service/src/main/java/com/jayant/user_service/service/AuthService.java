package com.jayant.user_service.service;

import com.jayant.user_service.dto.AuthenticationRequest;
import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
