package org.svetso.marketplace_monolyth.product.application.product.dto.response;

import java.util.List;

public record ProductPageDto(
        List<ProductDto> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {}
