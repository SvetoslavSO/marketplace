package org.svetso.marketplace_monolyth.company.web.dto.request;

import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

public record AddMemberRequest(
        Long userId,
        CompanyRole role
) {}
