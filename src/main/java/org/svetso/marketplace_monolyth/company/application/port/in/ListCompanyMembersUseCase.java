package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;

import java.util.List;

public interface ListCompanyMembersUseCase {
    List<CompanyMemberDto> execute(Long id);
}
