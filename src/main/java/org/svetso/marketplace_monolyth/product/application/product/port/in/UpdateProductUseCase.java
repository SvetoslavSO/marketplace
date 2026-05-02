package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.command.UpdateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;

public interface UpdateProductUseCase {
    ProductDto execute(UpdateProductCommand command);
}
