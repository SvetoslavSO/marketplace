package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.RemoveCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.RemoveMemberFromCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RemoveMemberFromCompanyService implements RemoveMemberFromCompanyUseCase {

    private final CompanyMemberRepository companyMemberRepository;

    public void execute(RemoveCompanyMemberCommand command) {
        CompanyMember companyMember = companyMemberRepository.getCompanyMemberByUserIdAndCompanyId(
                command.requesterId(),
                command.companyId()
        );

        if (!companyMember.isOwnerOrHr()) {
            throw new ForbiddenException("Only owner or hr can remove members");
        }

        companyMemberRepository.deleteByUserIdAndCompanyId(
                command.memberId(),
                command.companyId()
        );
    }

}
