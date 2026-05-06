package org.svetso.marketplace_monolyth.product.application.product.port.in;

import org.svetso.marketplace_monolyth.product.application.product.dto.command.GetProductBySellerCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;

public interface ListProductsBySellerUseCase {
    ProductPageDto execute(GetProductBySellerCommand command, int page, int size);
}
