package org.svetso.marketplace_monolyth.product.web.dto.response;


import java.util.List;

public record ProductPageResponse(
        List<ProductResponse> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {}
