package org.svetso.marketplace_monolyth.company.application.dto.response;

public record CreateCompanyDto(
        Long id,
        String name,
        String phone,
        String email
) {}
