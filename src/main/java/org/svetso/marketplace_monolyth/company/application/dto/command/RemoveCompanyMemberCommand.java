package org.svetso.marketplace_monolyth.company.application.dto.command;

public record RemoveCompanyMemberCommand(
        Long requesterId,
        Long memberId,
        Long companyId
) {}
