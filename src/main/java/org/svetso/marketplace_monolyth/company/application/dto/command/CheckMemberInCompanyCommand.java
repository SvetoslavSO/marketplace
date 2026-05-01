package org.svetso.marketplace_monolyth.company.application.dto.command;

public record CheckMemberInCompanyCommand(
        Long companyId,
        Long userId
) {}
