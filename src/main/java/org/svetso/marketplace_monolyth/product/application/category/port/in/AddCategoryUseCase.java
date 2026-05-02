package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.application.category.dto.command.AddCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;

public interface AddCategoryUseCase {
    CategoryDto execute(AddCategoryCommand command);
}
