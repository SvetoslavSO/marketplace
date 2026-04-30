package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.ListCompanyMembersUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListCompanyMembersService implements ListCompanyMembersUseCase {

    private final CompanyMemberRepository companyMemberRepository;
    private final ResponseMapper mapper;

    public List<CompanyMemberDto> execute(Long companyId) {
        return companyMemberRepository.getCompanyMembersByCompanyId(companyId)
                .stream()
                .map(mapper::companyMemberToDto)
                .toList();
    }
}
