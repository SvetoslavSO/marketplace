package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.ListProductsUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ListProductsService implements ListProductsUseCase {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public List<ProductDto> execute(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAll(page, size);

        return products.stream()
                .map(productDtoMapper::productToDto)
                .toList();
    }
}

//@Override
//public List<ProductDto> execute() {
//    return productRepository.findAll()
//            .stream()
//            .map(productDtoMapper::productToDto)
//            .toList();
//}