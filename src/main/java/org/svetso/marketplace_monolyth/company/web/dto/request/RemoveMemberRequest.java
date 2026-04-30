package org.svetso.marketplace_monolyth.company.web.dto.request;

public record RemoveMemberRequest(
        Long userId,
        Long companyId
) {}
