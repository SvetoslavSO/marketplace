package org.svetso.marketplace_monolyth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.svetso.marketplace_monolyth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.dto.UserCredentials;
import org.svetso.marketplace_monolyth.entity.User;
import org.svetso.marketplace_monolyth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    public void register(RegisterRequest request) {
        String keycloakId = keycloakService.createUser(request);

        User user = new User();
        user.setKeycloakId(keycloakId);
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        userRepository.save(user);
    }

    public void login(UserCredentials request) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        LoginRequest loginRequest = new LoginRequest();

        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        } else {
            loginRequest.setUsername(user.getName());
            loginRequest.setName(user.getName());
            loginRequest.setPassword(request.getPassword());
            loginRequest.setName(user.getName());

            keycloakService.getUserToken(loginRequest);
//            loginRequest.
//            keycloakService.getUserToken();
//            keycloakService.getUserToken();
        }
    }
}