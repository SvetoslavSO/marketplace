package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.application.category.dto.command.InsertCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;

public interface InsertBetweenCategoriesUseCase {
    CategoryDto execute(InsertCategoryCommand command);
}
