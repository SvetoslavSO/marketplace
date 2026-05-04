package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.domain.model.Company;

import java.util.List;

public interface ListCompaniesUseCase {
    List<CompanyDto> execute();
}
