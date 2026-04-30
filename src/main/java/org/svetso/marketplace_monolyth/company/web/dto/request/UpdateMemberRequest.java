package org.svetso.marketplace_monolyth.company.web.dto.request;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record UpdateMemberRequest(
        Long userId,
        Long companyId,
        CompanyRole role
) {}
