package org.svetso.marketplace_monolyth.company.application.port.out;

import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;
import org.svetso.marketplace_monolyth.user.entity.User;


import java.util.List;
import java.util.Optional;

public interface CompanyMemberRepository {
    void deleteByUserIdAndCompanyId(Long userId, Long companyId);
    List<CompanyMember> getCompanyMembersByUserId(Long userId);
    CompanyMember getCompanyMemberByUserIdAndCompanyId(Long userId, Long companyId);
    boolean existsByUserIdAndCompanyIdAndRole(Long userId, Long companyId, CompanyRole role);
    CompanyMember save(CompanyMember companyMember);
    List<CompanyMember> getCompanyMembersByCompanyId(Long companyId);
}
