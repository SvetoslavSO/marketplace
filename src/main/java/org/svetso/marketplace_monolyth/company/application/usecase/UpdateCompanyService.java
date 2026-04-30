package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.UpdateCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UpdateCompanyService implements UpdateCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final CompanyMemberRepository companyMemberRepository;
    private final ResponseMapper mapper;

    public CompanyDto execute(UpdateCompanyCommand updateCompanyCommand) {
        Company company = companyRepository
                .findById(updateCompanyCommand.companyId());
        CompanyMember companyMember = companyMemberRepository.getCompanyMemberByUserIdAndCompanyId(
                updateCompanyCommand.updaterId(),
                updateCompanyCommand.companyId()
        );

        if (!companyMember.isOwner()) {
            throw new ForbiddenException("Company can be updated only by admin or owner");
        }

        if (companyRepository.existsByEmail(updateCompanyCommand.email())
                && !company.getEmail().equals(updateCompanyCommand.email())) {
            throw new BadRequestException("Email already in use");
        }

        if (companyRepository.existsByPhone(updateCompanyCommand.phone())
                && !company.getPhone().equals(updateCompanyCommand.phone())) {
            throw new BadRequestException("Phone already in use");
        }

        company.updateDetails(
                updateCompanyCommand.name(),
                updateCompanyCommand.email(),
                updateCompanyCommand.phone()
        );

        Company createdCompany = companyRepository.save(company);

        return mapper.companyToDto(createdCompany);
    }
}
