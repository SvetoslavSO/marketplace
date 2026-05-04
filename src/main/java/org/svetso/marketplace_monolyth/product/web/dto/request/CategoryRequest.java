package org.svetso.marketplace_monolyth.product.web.dto.request;

public record CategoryRequest (
        Long id,
        String name,
        Long parentId
) {}