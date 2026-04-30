package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.DeleteCompanyCommand;

public interface DeleteCompanyUseCase {
    void execute(DeleteCompanyCommand command);
}
