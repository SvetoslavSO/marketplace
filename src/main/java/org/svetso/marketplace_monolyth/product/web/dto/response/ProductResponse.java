package org.svetso.marketplace_monolyth.product.web.dto.response;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.math.BigDecimal;

public record ProductResponse (
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Long categoryId,
        Long sellerId,
        SellerType sellerType
) {}