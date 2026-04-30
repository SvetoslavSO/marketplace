package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.DeleteMemberCommand;

public interface DeleteCompanyMemberUseCase {
    void execute(DeleteMemberCommand deleteCompanyCommand);
}
