package org.svetso.marketplace_monolyth.company.application.usecase;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.CreateCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CreateCompanyService implements CreateCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final CompanyMemberRepository companyMemberRepository;
    private final ResponseMapper mapper;

    public CompanyDto execute(CreateCompanyCommand command) {

        log.info("Attempt to create company {} in service", command.name());
        if (companyRepository.existsByEmail(command.email())) {
            throw new BadRequestException("Company email already exists");
        }

        if (companyRepository.existsByPhone(command.phone())) {
            throw new BadRequestException("Company phone already exists");
        }

        log.info("Attempt to create company after check phone and email");

        Company company = new Company(
                null,
                command.name(),
                command.email(),
                command.phone()
        );

        log.info("Attempt to create company with name {}", command.name());
        Company saved = companyRepository.save(company);
        log.info("Company saved");

        log.info("Attempt to create owner {} in company {} ", command.creatorUserId(), saved.getId());
        CompanyMember companyMember = companyMemberRepository.save(new CompanyMember(
                command.creatorUserId(),
                CompanyRole.OWNER,
                saved.getId()
        ));
        log.info("Owner saved");

        return mapper.companyToDto(saved);
    }
}
