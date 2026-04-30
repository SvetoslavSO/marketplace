package org.svetso.marketplace_monolyth.company.application.dto.command;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record UpdateCompanyMemberCommand(
        Long requesterId,
        Long userId,
        Long companyId,
        CompanyRole newRole
) {}
