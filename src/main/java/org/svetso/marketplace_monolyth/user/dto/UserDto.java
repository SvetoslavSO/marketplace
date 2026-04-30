package org.svetso.marketplace_monolyth.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private Long id;

    @NotNull
    private String keycloakId;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
