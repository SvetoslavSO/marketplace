package org.svetso.marketplace_monolyth.auth.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.svetso.marketplace_monolyth.auth.dto.LoginResponse;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.UnauthorizedException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakAuthClient {

    private final RestTemplate restTemplate;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.app.client-id}")
    private String clientId;

    public LoginResponse login(String username, String password) {

        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            Map<String, Object> data = response.getBody();

            return new LoginResponse(
                    (String) data.get("access_token"),
                    (String) data.get("refresh_token")
            );

        } catch (HttpClientErrorException e) {
            throw new UnauthorizedException("Invalid username or password");
        }
    }

    public LoginResponse refresh(String refreshToken) {

        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            Map<String, Object> data = response.getBody();

            return new LoginResponse(
                    (String) data.get("access_token"),
                    (String) data.get("refresh_token")
            );

        } catch (HttpClientErrorException e) {
            throw new UnauthorizedException("Invalid refresh token");
        }
    }
}
