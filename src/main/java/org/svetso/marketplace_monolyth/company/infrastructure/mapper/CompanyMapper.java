package org.svetso.marketplace_monolyth.company.infrastructure.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyEntity;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyMemberEntity;

import java.util.List;

@Component
public class CompanyMapper {

    public Company toDomain(CompanyEntity entity) {

        return new Company(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    public CompanyEntity toEntity(Company domain) {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setPhone(domain.getPhone());
        entity.setEmail(domain.getEmail());
        return entity;
    }

    public CompanyMember toDomainMember(CompanyMemberEntity entity) {
        return new CompanyMember(
                entity.getUserId(),
                entity.getRole(),
                entity.getCompany().getId()
        );
    }

    public CompanyMemberEntity createEntityMember(CompanyMember domain) {
        CompanyEntity companyRef = new CompanyEntity();
        companyRef.setId(domain.getCompanyId());

        return new CompanyMemberEntity(
                null,
                companyRef,
                domain.getUserId(),
                domain.getRole()
        );
    }
}
