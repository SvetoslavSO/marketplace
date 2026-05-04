package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

import java.util.List;

public interface ListParentCategoriesUseCase {
    List<CategoryDto> execute(Long id);
}
