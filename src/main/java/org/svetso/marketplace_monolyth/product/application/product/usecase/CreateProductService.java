package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.CheckMemberInCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.CheckMemberInCompanyUseCase;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.CreateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.CreateProductUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CheckMemberInCompanyUseCase checkMemberInCompanyUseCase;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public ProductDto execute(CreateProductCommand command) {
        log.info("Attempt to create product with name {} ", command.name());

        if (command.sellerType().equals(SellerType.COMPANY)) {
            if (!checkMemberInCompanyUseCase.execute(new CheckMemberInCompanyCommand(
                    command.sellerId(),
                    command.requesterId()
            ))) {
                throw new ForbiddenException("Only company members can add products to company");
            }
        }

        Product product = productDtoMapper.dtoToProduct(
                new ProductDto(
                        null,
                        command.name(),
                        command.description(),
                        command.price(),
                        command.stock(),
                        command.categoryId(),
                        command.sellerId(),
                        command.sellerType()
                )
        );

        return productDtoMapper.productToDto(productRepository.save(product));
    }
}
