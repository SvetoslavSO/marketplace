package org.svetso.marketplace_monolyth.product.application.product.dto.command;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.math.BigDecimal;

public record CreateProductCommand (
        Long requesterId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Long categoryId,
        SellerType sellerType,
        Long sellerId
) {}
