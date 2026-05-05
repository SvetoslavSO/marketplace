package org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<List<ProductEntity>> findBySellerIdAndSellerType(Long sellerId, SellerType sellerType);
    List<ProductEntity> findByCategoryIdIn(List<Long> categoryIds);
    Page<ProductEntity> findAll(Pageable pageable);
}
