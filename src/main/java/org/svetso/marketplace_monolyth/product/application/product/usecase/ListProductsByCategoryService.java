package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.product.application.category.port.in.GetDescendantCategoriesIdUseCase;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.ListProductsByCategoryUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProductsByCategoryService implements ListProductsByCategoryUseCase {

    private final ProductRepository productRepository;
    private final GetDescendantCategoriesIdUseCase getDescendantCategoriesIdUseCase;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public List<ProductDto> execute(Long categoryId) {
        List<Long> categoryIds = getDescendantCategoriesIdUseCase.execute(categoryId);

        List<Product> products = productRepository.findByCategoryIds(categoryIds);

        return products.stream()
                .map(productDtoMapper::productToDto)
                .toList();
    }
}
