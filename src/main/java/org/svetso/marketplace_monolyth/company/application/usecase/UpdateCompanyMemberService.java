package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.application.mapper.ResponseMapper;
import org.svetso.marketplace_monolyth.company.application.port.in.UpdateCompanyMemberUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UpdateCompanyMemberService implements UpdateCompanyMemberUseCase {
    private final CompanyMemberRepository companyMemberRepository;
    private final ResponseMapper responseMapper;

    @Override
    public CompanyMemberDto execute(UpdateCompanyMemberCommand command) {
        CompanyMember requester = companyMemberRepository.getCompanyMemberByUserIdAndCompanyId(
                command.requesterId(),
                command.companyId()
        );

        if (!requester.isOwnerOrHr()) {
            throw new ForbiddenException("Only owner or he can update company members");
        }

        CompanyMember memberToUpdate = companyMemberRepository.getCompanyMemberByUserIdAndCompanyId(
                command.userId(),
                command.companyId()
        );

        memberToUpdate.updateDetails(command.newRole());
        companyMemberRepository.save(memberToUpdate);
        return responseMapper.companyMemberToDto(memberToUpdate);
    }
}
