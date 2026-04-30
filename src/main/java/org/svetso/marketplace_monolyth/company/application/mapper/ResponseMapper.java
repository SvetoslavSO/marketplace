package org.svetso.marketplace_monolyth.company.application.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.application.dto.response.CreateCompanyDto;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;

@Component
public class ResponseMapper {
    public CreateCompanyDto companyToCreateDto(Company company) {
        return new CreateCompanyDto(
                company.getId(),
                company.getName(),
                company.getPhone(),
                company.getEmail()
        );
    }

    public CompanyDto companyToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getPhone(),
                company.getEmail()
        );
    }

    public CompanyMemberDto companyMemberToDto(CompanyMember member) {
        return new CompanyMemberDto(
                member.getUserId(),
                member.getRole()
        );
    }
}
