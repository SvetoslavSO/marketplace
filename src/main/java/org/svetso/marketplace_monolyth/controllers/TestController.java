package org.svetso.marketplace_monolyth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.dto.UserCredentials;
import org.svetso.marketplace_monolyth.services.UserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class TestController {

    private final UserService userService;

//    @GetMapping("/profile")
//    public Map<String, Object> profile(@AuthenticationPrincipal Jwt jwt) {
//        return Map.of(
//                "subject", jwt.getClaim("sub"),
//                "email", jwt.getClaim("email"),
//                "roles", jwt.getClaim("resource_access")
//        );
//    }


    @GetMapping("/seller-only")
    @PreAuthorize("hasAuthority('user')")
    public String sellerEndpoint() {
        return "Доступно только для пользователей с ролью user";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials request) {
        userService.login(request);

        return ResponseEntity.ok("login");
    }
}
