package org.svetso.marketplace_monolyth.company.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyEntity;

import java.util.List;
import java.util.Optional;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByEmail(String email);
    Optional<CompanyEntity> findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
