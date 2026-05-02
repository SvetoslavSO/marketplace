package org.svetso.marketplace_monolyth.company.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyMemberRepository;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyMember;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;
import org.svetso.marketplace_monolyth.company.infrastructure.mapper.CompanyMapper;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyMemberEntity;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.repository.JpaCompanyMemberRepository;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.CompanyMemberNotFoundException;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyMemberRepositoryImpl implements CompanyMemberRepository {

    private final JpaCompanyMemberRepository jpaCompanyMemberRepository;
    private final CompanyMapper mapper;

    @Override
    public CompanyMember save(CompanyMember companyMember) {
        boolean exists = jpaCompanyMemberRepository.existsByUserIdAndCompanyIdAndRole(
                companyMember.getUserId(),
                companyMember.getCompanyId(),
                companyMember.getRole()
        );

        if (exists) {
            throw new BadRequestException("Company member already exists");
        }

        CompanyMemberEntity entity = mapper.createEntityMember(companyMember);

        return mapper.toDomainMember(jpaCompanyMemberRepository.save(entity));
    }

    @Override
    public List<CompanyMember> getCompanyMembersByCompanyId(Long companyId) {
        return jpaCompanyMemberRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new NotFoundException("No company members in company"))
                .stream()
                .map(mapper::toDomainMember)
                .toList();
    }

    @Override
    public boolean existsByUserIdAndCompanyId(Long userId, Long companyId) {
        return jpaCompanyMemberRepository.existsByUserIdAndCompanyId(userId, companyId);
    }

    @Override
    public void deleteAllByCompanyId(Long companyId) {
        jpaCompanyMemberRepository.deleteAllByCompanyId(companyId);
    }

    @Override
    public void deleteByUserIdAndCompanyId(Long userId, Long companyId) {
        jpaCompanyMemberRepository.deleteByCompanyIdAndUserId(companyId, userId);
    }


    @Override
    public List<CompanyMember> getCompanyMembersByUserId(Long userId) {
        List<CompanyMemberEntity> companyMemberEntities = jpaCompanyMemberRepository
                .findCompanyMemberEntitiesByUserId(userId)
                .orElseThrow(() -> new NotFoundException("This user is not part of any companies"));
        return companyMemberEntities.stream()
                .map(mapper::toDomainMember)
                .toList();
    }

    @Override
    public CompanyMember getCompanyMemberByUserIdAndCompanyId(Long userId, Long companyId) {
        CompanyMemberEntity entity = jpaCompanyMemberRepository
                .findCompanyMemberEntityByUserIdAndCompanyId(userId, companyId)
                .orElseThrow(() -> new NotFoundException("User not found in company"));
        return mapper.toDomainMember(entity);
    }

//    @Override
//    public boolean existsByUserIdAndCompanyId(Long userId, Long companyId) {
//        return jpaCompanyMemberRepository
//                .findCompanyMemberEntityByUserIdAndCompanyId(userId, companyId).isPresent();
//    }

    @Override
    public boolean existsByUserIdAndCompanyIdAndRole(Long userId, Long companyId, CompanyRole role) {
        return jpaCompanyMemberRepository
                .existsByUserIdAndCompanyIdAndRole(userId, companyId, role);
    }
}
