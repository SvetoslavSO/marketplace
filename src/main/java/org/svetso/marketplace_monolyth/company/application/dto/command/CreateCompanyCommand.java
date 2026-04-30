package org.svetso.marketplace_monolyth.company.application.dto.command;

public record CreateCompanyCommand(
        String name,
        String email,
        String phone,
        Long creatorUserId
) {}
