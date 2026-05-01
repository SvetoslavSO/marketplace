package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.command.CreateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;

public interface CreateProductUseCase {
    ProductDto execute(CreateProductCommand command);
}
