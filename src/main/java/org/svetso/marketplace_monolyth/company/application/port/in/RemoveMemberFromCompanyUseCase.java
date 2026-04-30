package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.RemoveCompanyMemberCommand;

public interface RemoveMemberFromCompanyUseCase {
    void execute(RemoveCompanyMemberCommand command);
}
