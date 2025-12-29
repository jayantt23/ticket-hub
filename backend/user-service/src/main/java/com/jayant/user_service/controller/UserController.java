package com.jayant.user_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "Endpoints for managing users")
public class UserController {

    @GetMapping("/status")
    @Operation(summary = "Health Check")
    public String getStatus() {
        return "User Service is up and running on Port 8081";
    }
}
