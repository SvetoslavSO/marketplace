package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.GetProductBySellerCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.ListProductsBySellerUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ListProductsBySellerService implements ListProductsBySellerUseCase {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public List<ProductDto> execute(GetProductBySellerCommand command) {
        return productRepository.findBySellerIdAndSellerType(command.sellerId(), command.sellerType())
                .stream()
                .map(productDtoMapper::productToDto)
                .toList();
    }
}
