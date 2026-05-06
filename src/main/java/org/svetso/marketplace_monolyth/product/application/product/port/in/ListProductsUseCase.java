package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;

public interface ListProductsUseCase {
    ProductPageDto execute(int page, int size);
}
