package org.svetso.marketplace_monolyth.product.web.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.GetProductBySellerCommand;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductPageDto;
import org.svetso.marketplace_monolyth.product.web.dto.request.GetProductBySellerRequest;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductPageResponse;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductResponse;

import java.util.List;

@Component
public class WebResponseProductMapper {
    public ProductResponse productDtoToResponse(ProductDto dto) {
        return new ProductResponse(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stock(),
                dto.categoryId(),
                dto.sellerId(),
                dto.sellerType()
        );
    }

    public GetProductBySellerCommand productsBySellerToCommand(GetProductBySellerRequest request) {
        return new GetProductBySellerCommand(
                request.sellerId(),
                request.sellerType()
        );
    }


    public ProductPageResponse pageDtoToResponse(ProductPageDto dto) {
        List<ProductResponse> productResponseList = dto.items().stream().map(this::productDtoToResponse).toList();
        return new ProductPageResponse(
                productResponseList,
                dto.page(),
                dto.size(),
                dto.totalElements(),
                dto.totalPages(),
                dto.hasNext()
        );
    }
}
