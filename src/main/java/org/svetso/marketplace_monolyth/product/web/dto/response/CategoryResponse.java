package org.svetso.marketplace_monolyth.product.web.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        Long parentId
) {}
