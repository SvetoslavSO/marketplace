package org.svetso.marketplace_monolyth.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.svetso.marketplace_monolyth.auth.entity.User;

@Entity
@Table(name = "company_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompenyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private User user;
}
