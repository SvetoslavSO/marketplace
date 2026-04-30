package org.svetso.marketplace_monolyth.company.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.company.application.port.out.CompanyRepository;
import org.svetso.marketplace_monolyth.company.domain.model.Company;
import org.svetso.marketplace_monolyth.company.infrastructure.mapper.CompanyMapper;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity.CompanyEntity;
import org.svetso.marketplace_monolyth.company.infrastructure.persistence.repository.JpaCompanyRepository;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {
    private static final Logger log = LoggerFactory.getLogger(CompanyRepositoryImpl.class);
    private final JpaCompanyRepository jpaCompanyRepository;
    private final CompanyMapper mapper;

    @Override
    public Company save(Company company) {
        if (company.getId() == null) {
            CompanyEntity entity = mapper.toEntity(company);
            return mapper.toDomain(jpaCompanyRepository.save(entity));
        }

        CompanyEntity entity = jpaCompanyRepository.findById(company.getId())
                .orElseThrow(() -> new NotFoundException("Company not found"));
        entity.setName(company.getName());
        entity.setEmail(company.getEmail());
        entity.setPhone(company.getPhone());
        return mapper.toDomain(entity);
    }

    @Override
    public Company findById(Long id) {
        log.info("attempt to find company by id {}", id);
        CompanyEntity entity = jpaCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found by id"));
        return mapper.toDomain(entity);
    }

    @Override
    public Company findByEmail(String email) {
        CompanyEntity entity = jpaCompanyRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Company not found by email"));
        return mapper.toDomain(entity);
    }

    @Override
    public Company findByPhone(String phone) {
        CompanyEntity entity = jpaCompanyRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("Company not found by phone"));
        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaCompanyRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return jpaCompanyRepository.existsByPhone(phone);
    }

    @Override
    public void delete(Long companyId) {
        CompanyEntity entity = jpaCompanyRepository.findById(companyId)
                        .orElseThrow(() -> new NotFoundException("Company not found"));
        jpaCompanyRepository.delete(entity);
    }

    @Override
    public boolean existsById(Long companyId) {
        return jpaCompanyRepository.existsById(companyId);
    }

    @Override
    public List<Company> findAll() {
        return jpaCompanyRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
