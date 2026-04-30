package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.AddMemberCommand;

public interface AddMemberToCompanyUseCase {
    void execute(AddMemberCommand command);
}
