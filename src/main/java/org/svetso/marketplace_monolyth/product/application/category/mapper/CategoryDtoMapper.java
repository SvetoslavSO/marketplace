package org.svetso.marketplace_monolyth.product.application.category.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

@Component
public class CategoryDtoMapper {

    public Category dtoToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.id(),
                categoryDto.name(),
                categoryDto.parentId()
        );
    }

    public CategoryDto categoryToDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getParentId()
        );
    }

}
