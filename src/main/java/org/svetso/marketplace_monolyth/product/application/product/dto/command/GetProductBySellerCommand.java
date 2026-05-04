package org.svetso.marketplace_monolyth.product.application.product.dto.command;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

public record GetProductBySellerCommand(
        Long sellerId,
        SellerType sellerType
) {}
