package org.svetso.marketplace_monolyth.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.svetso.marketplace_monolyth.BaseEntity;
import org.svetso.marketplace_monolyth.product.entity.Product;
import org.svetso.marketplace_monolyth.user.entity.User;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private short rating;

    @Column(columnDefinition = "TEXT")
    private String reviewText;
}