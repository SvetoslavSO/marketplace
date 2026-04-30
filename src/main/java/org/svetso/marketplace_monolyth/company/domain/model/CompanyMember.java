package org.svetso.marketplace_monolyth.company.domain.model;

import java.util.Objects;

public class CompanyMember {

    private Long userId;
    private CompanyRole role;
    private Long companyId;

    public CompanyMember(Long userId, CompanyRole role, Long companyId) {
        this.userId = userId;
        this.role = role;
        this.companyId = companyId;
    }

    public boolean isOwner() {
        return role == CompanyRole.OWNER;
    }

    public boolean isAdmin() {
        return role == CompanyRole.ADMIN;
    }

    public boolean isEmployee() {
        return role == CompanyRole.EMPLOYEE;
    }

    public Long getUserId() {
        return userId;
    }

    public CompanyRole getRole() {
        return role;
    }

    public Long getCompanyId() { return companyId; }

    public boolean isOwnerOrHr() {
        return ( role == CompanyRole.HR || role == CompanyRole.OWNER);
    }

    public void updateDetails(CompanyRole role) {
        this.role = role;
    }

    public boolean isHr() { return role == CompanyRole.HR; }
}
