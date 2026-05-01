package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;

public interface GetProductUseCase {
    public ProductDto execute(Long id);
}
