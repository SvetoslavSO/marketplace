package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.FilterProductsCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;
import org.svetso.marketplace_monolyth.product.application.product.port.in.ListProductsByFiltersUseCase;

@Service
@RequiredArgsConstructor
public class ListProductsByFilterService implements ListProductsByFiltersUseCase {



    @Override
    public ProductPageDto execute(FilterProductsCommand command, int page, int size) {
        return null;
    }
}
