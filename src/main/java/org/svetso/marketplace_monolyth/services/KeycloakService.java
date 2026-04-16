package org.svetso.marketplace_monolyth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.svetso.marketplace_monolyth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.entity.User;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final RestTemplate restTemplate;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    /**
     * Получаем admin token
     */
    public String getAdminToken() {
        String url = serverUrl + "/realms/marketplace/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&grant_type=client_credentials";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody().get("access_token").toString();
    }

    public String createUser(RegisterRequest request) {
        try {
            String token = getAdminToken();

            String url = serverUrl + "/admin/realms/" + realm + "/users";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> user = Map.of(
                    "username", request.getUsername(),
                    "email", request.getEmail(),
                    "enabled", true,
                    "credentials", List.of(Map.of(
                            "type", "password",
                            "value", request.getPassword(),
                            "temporary", false
                    ))
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(user, headers);

            ResponseEntity<Void> response =
                    restTemplate.postForEntity(url, entity, Void.class);

            String location = response.getHeaders().getLocation().toString();
            return location.substring(location.lastIndexOf("/") + 1);

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Keycloak error: " + e.getResponseBodyAsString());
        }
    }

    public void assignRole(String userId, String roleName) {
        String token = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        // 1. получаем client UUID
        String clientUrl = serverUrl + "/admin/realms/" + realm + "/clients?clientId=marketplace-backend";

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List> clientResponse =
                restTemplate.exchange(clientUrl, HttpMethod.GET, entity, List.class);

        Map client = (Map) clientResponse.getBody().get(0);
        String clientUuid = client.get("id").toString();

        // 2. получаем роль
        String roleUrl = serverUrl + "/admin/realms/" + realm +
                "/clients/" + clientUuid + "/roles/" + roleName;

        ResponseEntity<Map> roleResponse =
                restTemplate.exchange(roleUrl, HttpMethod.GET, entity, Map.class);

        Map role = roleResponse.getBody();

        // 3. назначаем роль
        String assignUrl = serverUrl + "/admin/realms/" + realm +
                "/users/" + userId + "/role-mappings/clients/" + clientUuid;

        HttpHeaders assignHeaders = new HttpHeaders();
        assignHeaders.setBearerAuth(token);
        assignHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Map>> assignEntity =
                new HttpEntity<>(List.of(role), assignHeaders);

        restTemplate.postForEntity(assignUrl, assignEntity, Void.class);
    }

    public String getUserToken(LoginRequest request) {
        String adminToken = getAdminToken();//

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);

        String clientUrl = serverUrl + "/admin/realms/" + realm + "/clients?clientId=marketplace-backend";

    }
}