package org.svetso.marketplace_monolyth.product.web.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.web.dto.response.CategoryResponse;

@Component
public class WebResponseCategoryMapper {


    public CategoryResponse dtoToResponse(CategoryDto categoryDto) {
        return new CategoryResponse (
                categoryDto.id(),
                categoryDto.name(),
                categoryDto.parentId()
        );
    }
}
