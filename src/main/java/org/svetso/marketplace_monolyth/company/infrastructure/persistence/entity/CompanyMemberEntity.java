package org.svetso.marketplace_monolyth.company.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.svetso.marketplace_monolyth.BaseEntity;
import org.svetso.marketplace_monolyth.company.domain.model.CompanyRole;

@Entity
@Table(name = "company_members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_role", nullable = false)
    private CompanyRole role;
}
