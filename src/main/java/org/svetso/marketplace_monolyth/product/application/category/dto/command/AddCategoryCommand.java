package org.svetso.marketplace_monolyth.product.application.category.dto.command;

public record AddCategoryCommand(
        Long requesterId,
        String name,
        Long parentId
) {}
