package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.company.application.port.in.ListCompaniesUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListCompaniesService implements ListCompaniesUseCase {
    private final CompanyRepository companyRepository;

    @Override
    public List<Company> execute() {
        return companyRepository.findAll();
    }
}
