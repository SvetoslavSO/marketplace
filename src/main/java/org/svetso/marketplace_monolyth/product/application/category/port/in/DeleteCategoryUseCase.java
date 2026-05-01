package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.application.category.dto.command.DeleteCategoryCommand;

public interface DeleteCategoryUseCase {
    void execute(DeleteCategoryCommand command);
}
