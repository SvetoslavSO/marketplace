package org.svetso.marketplace_monolyth.company.web.dto.response;

public record CompanyResponse (
        Long companyId,
        String name,
        String email,
        String phone
) {}
