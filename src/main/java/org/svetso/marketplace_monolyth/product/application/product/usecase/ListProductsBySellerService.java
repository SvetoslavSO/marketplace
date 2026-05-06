package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.GetProductBySellerCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.ListProductsBySellerUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProductsBySellerService implements ListProductsBySellerUseCase {
    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;
    @Override
    public ProductPageDto execute(GetProductBySellerCommand command, int page, int size) {
        validatePagination(page, size);
        Page<Product> productPage = productRepository.findBySellerIdAndSellerType(
                command.sellerId(),
                command.sellerType(),
                page,
                size
        );
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