package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.GetProductUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GetProductService implements GetProductUseCase {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public ProductDto execute(Long id) {
        return productDtoMapper.productToDto(productRepository.findById(id));
    }
}
