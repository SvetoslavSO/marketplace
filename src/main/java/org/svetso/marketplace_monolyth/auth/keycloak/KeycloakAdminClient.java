package org.svetso.marketplace_monolyth.auth.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.svetso.marketplace_monolyth.auth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.KeycloakException;
import org.svetso.marketplace_monolyth.exceptions.UnauthorizedException;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakAdminClient {

    private final RestTemplate restTemplate;
    private final KeycloakTokenManager tokenManager;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    public String createUser(RegisterRequest request) {

        String url = serverUrl + "/admin/realms/" + realm + "/users";

        HttpHeaders headers = authHeaders();
        Map<String, Object> user = Map.of(
                "username", request.getUsername(),
                "email", request.getEmail(),
                "firstName", request.getFirstName(),
                "lastName", request.getLastName(),
                "emailVerified", true,
                "enabled", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", request.getPassword(),
                        "temporary", false
                ))
        );

        try {
            ResponseEntity<Void> response =
                    restTemplate.postForEntity(url, new HttpEntity<>(user, headers), Void.class);
            String location = response.getHeaders().getLocation().toString();
            return location.substring(location.lastIndexOf("/") + 1);
        } catch (HttpClientErrorException e) {
            log.error("Keycloak error: {}", e.getResponseBodyAsString());
            if (e.getStatusCode().isSameCodeAs(HttpStatus.UNAUTHORIZED)) {
                throw new UnauthorizedException("Invalid credentials");
            }
            throw new KeycloakException("Keycloak request failed");
        }
    }

    public void assignClientRole(String userId, String clientId, String roleName) {

        String clientUuid = getClientUuid(clientId);

        Map role = getRole(clientUuid, roleName);

        String url = serverUrl + "/admin/realms/" + realm +
                "/users/" + userId +
                "/role-mappings/clients/" + clientUuid;

        restTemplate.postForEntity(url,
                new HttpEntity<>(List.of(role), authJsonHeaders()),
                Void.class);
    }

    private String getClientUuid(String clientId) {

        String url = serverUrl + "/admin/realms/" + realm +
                "/clients?clientId=" + clientId;

        ResponseEntity<List> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(authHeaders()), List.class);

        Map map = (Map) response.getBody().get(0);
        return (String) map.get("id");
    }

    private Map getRole(String clientUuid, String roleName) {

        String url = serverUrl + "/admin/realms/" + realm +
                "/clients/" + clientUuid +
                "/roles/" + roleName;

        ResponseEntity<Map> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(authHeaders()), Map.class);

        return response.getBody();
    }

    private HttpHeaders authHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenManager.getToken());
        return headers;
    }

    private HttpHeaders authJsonHeaders() {
        HttpHeaders headers = authHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
