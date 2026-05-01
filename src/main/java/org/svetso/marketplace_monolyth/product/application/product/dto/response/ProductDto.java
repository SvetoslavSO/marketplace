package org.svetso.marketplace_monolyth.product.application.product.dto.response;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.math.BigDecimal;

public record ProductDto (
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Long categoryId,
        Long sellerId,
        SellerType sellerType
) {}
