package org.svetso.marketplace_monolyth.company.application.port.out;
import org.svetso.marketplace_monolyth.company.domain.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    Company save(Company company);
    Company findById(Long id);
    Company findByEmail(String email);
    Company findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    void delete(Long companyId);
    boolean existsById(Long companyId);
    List<Company> findAll();
}
