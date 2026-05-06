package org.svetso.marketplace_monolyth.company.web.dto.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.web.dto.request.CreateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.UpdateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.response.CompanyResponse;

@Component
public class CompanyMapper {


    public CreateCompanyCommand createCompanyRequestToCommand(CreateCompanyRequest request, Long creatorId) {
        return new CreateCompanyCommand(
                request.name(),
                request.email(),
                request.phone(),
                creatorId
        );
    }

    public UpdateCompanyCommand updateRequestToCommand(UpdateCompanyRequest request, Long updaterId) {
        return new UpdateCompanyCommand(
                request.companyId(),
                request.name(),
                request.email(),
                request.phone(),
                updaterId
        );
    }

    public CompanyResponse dtoToResponse(CompanyDto dto) {
        return new CompanyResponse(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.phone()
        );
    }
}
