package org.svetso.marketplace_monolyth.auth.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.svetso.marketplace_monolyth.exceptions.UnauthorizedException;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakTokenManager {

    private final RestTemplate restTemplate;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    private String token;
    private Instant expiresAt;

    public String getToken() {
        if (token == null || Instant.now().isAfter(expiresAt)) {
            refreshToken();
        }
        return token;
    }

    private void refreshToken() {
        String url = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            Map<String, Object> map = response.getBody();

            this.token = (String) map.get("access_token");

            Integer expiresIn = (Integer) map.get("expires_in");
            this.expiresAt = Instant.now().plusSeconds(expiresIn - 30);
        } catch (HttpClientErrorException e) {
            log.error("Keycloak error: {}", e.getResponseBodyAsString());
            throw new UnauthorizedException("Refresh token expired");
        }
    }
}