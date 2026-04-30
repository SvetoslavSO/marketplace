package org.svetso.marketplace_monolyth.company.application.dto.response;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record CompanyMemberDto(
        Long userId,
        CompanyRole role
) {}
