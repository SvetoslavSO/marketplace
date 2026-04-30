package org.svetso.marketplace_monolyth.company.application.dto.command;

public record DeleteMemberCommand(
        Long requesterId,
        Long userId,
        Long companyId
) {}