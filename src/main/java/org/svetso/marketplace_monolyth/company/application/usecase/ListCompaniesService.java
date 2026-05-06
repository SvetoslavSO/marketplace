package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.ListCompaniesUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListCompaniesService implements ListCompaniesUseCase {
    private final CompanyRepository companyRepository;
    private final ResponseMapper responseMapper;

    @Override
    public List<CompanyDto> execute() {
        return companyRepository.findAll()
                .stream()
                .map(responseMapper::companyToDto)
                .toList();
    }
}
