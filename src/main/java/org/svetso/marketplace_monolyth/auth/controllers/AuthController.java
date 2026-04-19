package org.svetso.marketplace_monolyth.auth.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.auth.dto.LoginRequest;
import org.svetso.marketplace_monolyth.auth.dto.LoginResponse;
import org.svetso.marketplace_monolyth.auth.dto.RefreshTokenRequest;
import org.svetso.marketplace_monolyth.auth.dto.RegisterRequest;
import org.svetso.marketplace_monolyth.auth.services.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/seller-only")
    @PreAuthorize("hasRole('user')")
    public String sellerEndpoint() {
        return "Доступно только для пользователей с ролью user";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refresh(request.getRefreshToken()));
    }
}
