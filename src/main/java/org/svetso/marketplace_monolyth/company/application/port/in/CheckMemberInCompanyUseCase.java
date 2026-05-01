package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.CheckMemberInCompanyCommand;

public interface CheckMemberInCompanyUseCase {
    boolean execute(CheckMemberInCompanyCommand command);
}
