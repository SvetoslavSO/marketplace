package org.svetso.marketplace_monolyth.product.infrastructure.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.ProductEntity;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCategory().getId(),
                entity.getSellerId(),
                entity.getSellerType()
        );
    }

    public ProductEntity toEntity(Product domain, CategoryEntity categoryRef) {
        return new ProductEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getPrice(),
                domain.getStock(),
                categoryRef,
                domain.getSellerId(),
                domain.getSellerType()
        );
    }

}
