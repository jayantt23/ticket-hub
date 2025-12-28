package com.jayant.user_service.service.impl;

import com.jayant.user_service.dto.AuthenticationRequest;
import com.jayant.user_service.dto.AuthenticationResponse;
import com.jayant.user_service.dto.RegisterRequest;
import com.jayant.user_service.entity.Role;
import com.jayant.user_service.entity.User;
import com.jayant.user_service.repository.UserRepository;
import com.jayant.user_service.service.AuthService;
import com.jayant.user_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User does not exist!"));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
