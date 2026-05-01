package org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(Long id);
    Optional<CategoryEntity> findByName(String name);
}
