package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.DeleteCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.DeleteCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DeleteCompanyService implements DeleteCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMemberRepository companyMemberRepository;

    public void execute(DeleteCompanyCommand command) {

        if (!companyRepository.existsById(command.companyId())) {
            throw new NotFoundException("Company not found");
        }

        boolean isOwner = companyMemberRepository.existsByUserIdAndCompanyIdAndRole(
                command.requesterId(),
                command.companyId(),
                CompanyRole.OWNER
        );
        if (!isOwner) {
            throw new ForbiddenException("Only owner can delete company");
        }
        companyMemberRepository.deleteAllByCompanyId(command.companyId());
        companyRepository.delete(command.companyId());
    }
}
