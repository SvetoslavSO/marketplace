package org.svetso.marketplace_monolyth.auth.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.auth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.auth.dto.LoginResponse;
import org.svetso.marketplace_monolyth.auth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.user.dto.UserCreationDto;
import org.svetso.marketplace_monolyth.auth.keycloak.KeycloakAdminClient;
import org.svetso.marketplace_monolyth.auth.keycloak.KeycloakAuthClient;
import org.svetso.marketplace_monolyth.user.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final KeycloakAuthClient authClient;
    private final KeycloakAdminClient adminClient;
    private final UserService userService;
    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for {}", request.getUsername());

        return authClient.login(
                request.getUsername(),
                request.getPassword()
        );
    }

    public void register(RegisterRequest request) {
        log.info("Register user {}", request.getUsername());

        try {

            userService.isEmailExists(request.getEmail());

            userService.isUsernameExists(request.getUsername());

            String keycloakId = adminClient.createUser(request);

            adminClient.assignClientRole(
                    keycloakId,
                    "marketplace-backend",
                    "user"
            );

            UserCreationDto userCreationDto = new UserCreationDto();
            userCreationDto.setEmail(request.getEmail());
            userCreationDto.setKeycloakId(keycloakId);
            userCreationDto.setFirstName(request.getFirstName());
            userCreationDto.setUsername(request.getUsername());
            userCreationDto.setLastName(request.getLastName());

            userService.createUser(userCreationDto);

        } catch (Exception e) {
            log.error("Register failed for {}", request.getUsername(), e);
            throw e;
        }
    }

    public LoginResponse refresh(String refreshToken) {
        return authClient.refresh(refreshToken);
    }
}
