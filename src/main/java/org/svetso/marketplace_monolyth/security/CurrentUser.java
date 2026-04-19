package org.svetso.marketplace_monolyth.security;

import java.util.List;

public record CurrentUser(
        Long userId,
        String keycloakId,
        String email,
        List<String> roles
) {}
