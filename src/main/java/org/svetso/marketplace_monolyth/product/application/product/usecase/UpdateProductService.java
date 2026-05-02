package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.CheckMemberInCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.CheckMemberInCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.UpdateCompanyMemberUseCase;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.UpdateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.mapper.ProductDtoMapper;
import org.svetso.marketplace_monolyth.product.application.product.port.in.UpdateProductUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CheckMemberInCompanyUseCase checkMemberInCompanyUseCase;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public ProductDto execute(UpdateProductCommand command) {
        log.info("Attempt to update product with name {} ", command.name());

        if (command.sellerType().equals(SellerType.COMPANY)) {
            if (!checkMemberInCompanyUseCase.execute(new CheckMemberInCompanyCommand(
                    product.getSellerId(),
                    command.requesterId()
            ))) {
                throw new ForbiddenException("Only company members can add products to company");
            }
        }

        Product product = productRepository.findById(command.productId());

        product.updateDetails(
                command.name(),
                command.description(),
                command.price(),
                command.stock(),
                command.categoryId()
        );

        productRepository.save(product);
        log.info("Product updated {} ", product.getName());

        return productDtoMapper.productToDto(product);
    }
}
