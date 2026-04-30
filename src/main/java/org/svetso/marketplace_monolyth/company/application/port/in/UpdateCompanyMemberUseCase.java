package org.svetso.marketplace_monolyth.company.application.port.in;

import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;

public interface UpdateCompanyMemberUseCase {
    CompanyMember execute(UpdateCompanyMemberCommand command);
}
