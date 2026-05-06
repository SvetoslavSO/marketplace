package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;

public interface ListProductsByCategoryUseCase {
    ProductPageDto execute(Long categoryId, int page, int size);
}
