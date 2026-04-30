package org.svetso.marketplace_monolyth.company.application.dto.command;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record AddMemberCommand(
        Long requesterId,
        Long companyId,
        Long userId,
        CompanyRole role
) {}
