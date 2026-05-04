package org.svetso.marketplace_monolyth.product.web.dto.request;

public record CreateCategoryRequest (
        Long requesterId,
        String name,
        Long parentId
){}
