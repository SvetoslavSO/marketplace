package org.svetso.marketplace_monolyth.product.application.product.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.product.dto.response.ProductDto;
import org.svetso.marketplace_monolyth.product.domain.model.Product;

@Component
public class ProductDtoMapper {

    public ProductDto productToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategoryId(),
                product.getSellerId(),
                product.getSellerType()
        );
    }

    public Product dtoToProduct(ProductDto dto) {
        return new Product(
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
}
