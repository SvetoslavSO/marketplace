package org.svetso.marketplace_monolyth.company.application.dto.command;

public record DeleteCompanyCommand(
        Long requesterId,
        Long companyId
) {}
