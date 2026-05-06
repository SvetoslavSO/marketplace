package org.svetso.marketplace_monolyth.company.web.dto.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.company.application.dto.command.AddMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.RemoveCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.web.dto.request.AddMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.CreateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.RemoveMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.UpdateMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.response.CompanyMemberResponse;

@Component
public class CompanyEmployeeMapper {

    public AddMemberCommand addMemberRequestToCommand(AddMemberRequest request, Long creatorId) {
        return new AddMemberCommand(
                creatorId,
                request.companyId(),
                request.userId(),
                request.role()
        );
    }

    public RemoveCompanyMemberCommand removeRequestToCommand(Long companyId, Long userId, Long requesterId) {
           return new RemoveCompanyMemberCommand(
                   requesterId,
                   userId,
                   companyId
           );
    }

    public UpdateCompanyMemberCommand updateRequestToCommand(UpdateMemberRequest request, Long requesterId) {
        return new UpdateCompanyMemberCommand(
                requesterId,
                request.userId(),
                request.companyId(),
                request.role()
        );
    }

    public CompanyMemberResponse companyMemberDtoToResponse(CompanyMemberDto dto) {
        return new CompanyMemberResponse(
                dto.userId(),
                dto.role()
        );
    }

}
