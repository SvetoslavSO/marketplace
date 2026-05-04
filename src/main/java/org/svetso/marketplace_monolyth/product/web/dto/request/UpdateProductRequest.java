package org.svetso.marketplace_monolyth.product.web.dto.request;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Long categoryId,
        SellerType sellerType,
        Long sellerId
) {}
