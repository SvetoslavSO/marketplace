package org.svetso.marketplace_monolyth.company.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.CheckMemberInCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.CheckMemberInCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CheckMemberIncCompanyService implements CheckMemberInCompanyUseCase {

    private final CompanyMemberRepository companyMemberRepository;

    public boolean execute(CheckMemberInCompanyCommand command) {
        return companyMemberRepository.existsByUserIdAndCompanyId(
                command.userId(),
                command.companyId()
        );
    }
}
