package org.svetso.marketplace_monolyth.company.web.dto.request;

public record CreateCompanyRequest (
    String name,
    String phone,
    String email
) {}
