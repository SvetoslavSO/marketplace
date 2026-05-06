package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.product.application.category.port.in.GetDescendantCategoriesIdUseCase;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;
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
    public ProductPageDto execute(Long categoryId, int page, int size) {
        validatePagination(page, size);
        List<Long> categoryIds = getDescendantCategoriesIdUseCase.execute(categoryId);
        Page<Product> productPage = productRepository.findByCategoryIds(categoryIds, page, size);
        List<ProductDto> items = productPage.getContent().stream()
                .map(productDtoMapper::productToDto)
                .toList();
        return new ProductPageDto(
                items,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.hasNext()
        );
    }

    private void validatePagination(int page, int size) {
        if (page < 0) throw new BadRequestException("page must be >= 0");
        if (size <= 0 || size > 100) throw new BadRequestException("size must be between 1 and 100");
    }
}
