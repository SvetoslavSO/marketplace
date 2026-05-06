package org.svetso.marketplace_monolyth.product.web.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.CreateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.DeleteProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.GetProductBySellerCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.UpdateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.web.dto.request.CreateProductRequest;
import org.svetso.marketplace_monolyth.product.web.dto.request.UpdateProductRequest;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductPageResponse;

@Component
public class WebRequestProductMapper {


    public CreateProductCommand createRequestToCommand(Long userId, CreateProductRequest request) {
        return new CreateProductCommand(
                userId,
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.categoryId(),
                request.sellerType(),
                request.sellerId()
        );
    }

    public GetProductBySellerCommand sellerProductsCommand(Long sellerId, SellerType sellerType) {
        return new GetProductBySellerCommand(
                sellerId,
                sellerType
        );
    }

    public DeleteProductCommand deleteProductCommand(Long userId, Long productId) {
        return new DeleteProductCommand(
                userId,
                productId
        );
    }

    public UpdateProductCommand updateProductCommand(Long userId, Long productId, UpdateProductRequest request) {
        return new UpdateProductCommand(
                userId,
                productId,
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.categoryId(),
                request.sellerType(),
                request.sellerId()
        );
    }
}
