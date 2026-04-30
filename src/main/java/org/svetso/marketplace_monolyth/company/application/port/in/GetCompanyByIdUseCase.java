package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;

public interface GetCompanyByIdUseCase {
    CompanyDto execute(Long id);
}
