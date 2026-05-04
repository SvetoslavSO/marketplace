package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;

import java.util.List;

public interface ListProductsUseCase {
    List<ProductDto> execute(int page, int size);
}
