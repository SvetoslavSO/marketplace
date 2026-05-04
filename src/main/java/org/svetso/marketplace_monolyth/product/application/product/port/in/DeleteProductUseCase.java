package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.command.DeleteProductCommand;

public interface DeleteProductUseCase {
    void execute(DeleteProductCommand deleteProductCommand);
}
