package com.jayant.user_service.config;

import com.jayant.user_service.entity.Role;
import com.jayant.user_service.entity.User;
import com.jayant.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("admin@tickethub.com").isEmpty()) {

            User admin = User.builder()
                    .name("admin")
                    .email("admin@tickethub.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            log.info("Default Admin User created: admin@tickethub.com");
        } else {
            log.info("Admin User already exists.");
        }
    }
}
