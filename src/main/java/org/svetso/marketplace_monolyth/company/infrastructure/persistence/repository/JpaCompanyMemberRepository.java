package org.svetso.marketplace_monolyth.company.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyMemberEntity;

import java.util.List;
import java.util.Optional;

public interface JpaCompanyMemberRepository extends JpaRepository<CompanyMemberEntity, Long> {
    Optional<List<CompanyMemberEntity>> findByCompanyId(Long companyId);
    Optional<CompanyMemberEntity> findByCompanyIdAndUserId(Long companyId, Long userId);
    void deleteByCompanyIdAndUserId(Long companyId, Long userId);
    Optional<CompanyMemberEntity> findCompanyMemberEntityById(Long companyMemberId);
    Optional<List<CompanyMemberEntity>> findCompanyMemberEntitiesByUserId(Long userId);
    Optional<CompanyMemberEntity> findCompanyMemberEntityByUserIdAndCompanyId(Long userId, Long companyId);
    boolean existsByUserIdAndCompanyIdAndRole(Long userId, Long companyId, CompanyRole role);
    boolean existsByUserIdAndCompanyId(Long userId, Long companyId);
    @Modifying
    @Query("DELETE FROM CompanyMemberEntity cm WHERE cm.company.id = :companyId")
    void deleteAllByCompanyId(@Param("companyId") Long companyId);
}
