package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.application.category.dto.command.AddCategoryCommand;

public interface AddCategoryUseCase {
    void execute(AddCategoryCommand command);
}
