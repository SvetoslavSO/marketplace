package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;

import java.util.List;

public interface ListProductsByCategoryUseCase {
    List<ProductDto> execute(Long categoryId);
}
