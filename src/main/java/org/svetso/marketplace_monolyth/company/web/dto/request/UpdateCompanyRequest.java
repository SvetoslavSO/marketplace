package org.svetso.marketplace_monolyth.company.web.dto.request;

public record UpdateCompanyRequest(
        String name,
        String email,
        String phone
) {}
