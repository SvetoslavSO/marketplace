package org.svetso.marketplace_monolyth.product.web.dto.request;

import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

public record GetProductBySellerRequest(
        Long sellerId,
        SellerType sellerType
) {}
