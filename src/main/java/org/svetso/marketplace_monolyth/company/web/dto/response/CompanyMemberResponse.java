package org.svetso.marketplace_monolyth.company.web.dto.response;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record CompanyMemberResponse(
        Long userId,
        CompanyRole role
) {}
