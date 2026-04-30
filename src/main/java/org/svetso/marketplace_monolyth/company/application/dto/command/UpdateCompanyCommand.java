package org.svetso.marketplace_monolyth.company.application.dto.command;

public record UpdateCompanyCommand(
        Long companyId,
        String name,
        String email,
        String phone,
        Long updaterId
) {}
