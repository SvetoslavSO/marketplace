package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;

public interface CreateCompanyUseCase {
    CompanyDto execute(CreateCompanyCommand command);
}
