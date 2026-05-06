package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.command.FilterProductsCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;

public interface ListProductsByFiltersUseCase {
    ProductPageDto execute(FilterProductsCommand command, int page, int size);
}
