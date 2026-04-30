package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;

public interface UpdateCompanyUseCase {
    CompanyDto execute(UpdateCompanyCommand command);
}
