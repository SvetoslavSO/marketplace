package org.svetso.marketplace_monolyth.auth.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.auth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.auth.dto.LoginResponse;
import org.svetso.marketplace_monolyth.auth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.auth.entity.User;
import org.svetso.marketplace_monolyth.auth.keycloak.KeycloakAdminClient;
import org.svetso.marketplace_monolyth.auth.keycloak.KeycloakAuthClient;
import org.svetso.marketplace_monolyth.auth.repository.UserRepository;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final KeycloakAuthClient authClient;
    private final KeycloakAdminClient adminClient;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for {}", request.getUsername());

        return authClient.login(
                request.getUsername(),
                request.getPassword()
        );
    }

    public void register(RegisterRequest request) {
        log.info("Register user {}", request.getUsername());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        String keycloakId = adminClient.createUser(request);
        adminClient.assignClientRole(keycloakId, "marketplace-backend", "user");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setKeycloakId(keycloakId);
        user.setFirstName(request.getFirstName());
        user.setUsername(request.getUsername());
        user.setLastName(request.getLastName());

        userRepository.save(user);
        log.info("User {} registered successfully", request.getUsername());
    }

    public LoginResponse refresh(String refreshToken) {
        return authClient.refresh(refreshToken);
    }
}
