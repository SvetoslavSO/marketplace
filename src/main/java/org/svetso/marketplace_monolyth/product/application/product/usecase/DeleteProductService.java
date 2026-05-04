package org.svetso.marketplace_monolyth.product.application.product.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.company.application.dto.command.CheckMemberInCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.CheckMemberInCompanyUseCase;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.DeleteProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.port.in.DeleteProductUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepository productRepository;
    private final CheckMemberInCompanyUseCase checkMemberInCompanyUseCase;

    @Override
    public void execute(DeleteProductCommand command) {
        log.info("Attempt to delete product with id {}", command.productId());

        Product product = productRepository.findById(command.productId());

        if (product.getSellerType() == SellerType.COMPANY) {
            if (!checkMemberInCompanyUseCase.execute(
                    new CheckMemberInCompanyCommand(
                            product.getSellerId(),
                            command.requesterId()
                    )
            )) {
                throw new ForbiddenException("Only company members can update this product");
            }
        }

        if (product.getSellerType() == SellerType.USER) {
            if (!product.getSellerId().equals(command.requesterId())) {
                throw new ForbiddenException("You can update only your own products");
            }
        }

        productRepository.delete(product);
    }
}
