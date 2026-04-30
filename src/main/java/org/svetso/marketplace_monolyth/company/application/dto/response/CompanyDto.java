package org.svetso.marketplace_monolyth.company.application.dto.response;

public record CompanyDto(
        Long id,
        String name,
        String phone,
        String email
) {}
