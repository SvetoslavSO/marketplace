package org.svetso.marketplace_monolyth.product.application.category.dto.response;

public record CategoryDto
(
        Long id,
        String name,
        Long parentId
) {}
