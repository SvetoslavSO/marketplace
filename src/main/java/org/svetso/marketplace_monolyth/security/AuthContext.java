package org.svetso.marketplace_monolyth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.user.entity.User;
import org.svetso.marketplace_monolyth.user.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthContext {

    private final UserRepository userRepository;

    public CurrentUser getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new RuntimeException("No authenticated user");
        }

        String keycloakId = jwt.getSubject();
        String email = jwt.hasClaim("email") ? jwt.getClaim("email") : null;

        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new CurrentUser(
                user.getId(),
                keycloakId,
                email,
                extractRoles(jwt)
        );
    }

    @SuppressWarnings("unchecked")
    private List<String> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null) return List.of();

        Map<String, Object> client =
                (Map<String, Object>) resourceAccess.get("marketplace-backend");

        if (client == null) return List.of();

        List<String> roles = (List<String>) client.get("roles");
        return roles != null ? roles : List.of();
    }
}
