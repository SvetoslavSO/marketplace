package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.dto.response.CreateCompanyDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.GetCompanyByIdUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCompanyByIdService implements GetCompanyByIdUseCase {

    private final CompanyRepository companyRepository;
    private final ResponseMapper mapper;

    public CompanyDto execute(Long companyId) {
        log.info("Attempt to get id in service company with id {}", companyId);
        return mapper.companyToDto(companyRepository.findById(companyId));
    }
}
