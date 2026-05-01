package org.svetso.marketplace_monolyth.product.infrastructure.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.domain.model.Category;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;

@Component
public class CategoryMapper {
    public Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getParent().getId()
        );
    }

    public CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());

        if (category.getParentId() != null) {
            CategoryEntity parent = new CategoryEntity();
            parent.setId(category.getParentId());
            entity.setParent(parent);
        }

        return entity;
    }
}
