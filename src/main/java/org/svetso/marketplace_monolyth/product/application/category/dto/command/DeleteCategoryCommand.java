package org.svetso.marketplace_monolyth.product.application.category.dto.command;

public record DeleteCategoryCommand (
    Long requesterId,
    Long categoryId
) {}
