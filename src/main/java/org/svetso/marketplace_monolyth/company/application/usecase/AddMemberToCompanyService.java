package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.AddMemberCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.AddMemberToCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddMemberToCompanyService implements AddMemberToCompanyUseCase {
    private final CompanyMemberRepository companyMemberRepository;

    public void execute(AddMemberCommand addMemberCommand) {
        log.info("Attempt to add member with id {} in company {}",
                addMemberCommand.userId(),
                addMemberCommand.companyId());

        CompanyMember requester = companyMemberRepository.getCompanyMemberByUserIdAndCompanyId(
                addMemberCommand.requesterId(),
                addMemberCommand.companyId()
        );

        if (!requester.isOwnerOrHr()) {
            throw new ForbiddenException("only owner or hr can add members to company");
        }

        if (companyMemberRepository.existsByUserIdAndCompanyIdAndRole(
                addMemberCommand.userId(),
                addMemberCommand.companyId(),
                addMemberCommand.role())) {
            throw new BadRequestException("User already in company");
        }
        companyMemberRepository.save(
                new CompanyMember(
                        addMemberCommand.userId(),
                        addMemberCommand.role(),
                        addMemberCommand.companyId())
        );
        log.info("User added in company domain");
    }
}
