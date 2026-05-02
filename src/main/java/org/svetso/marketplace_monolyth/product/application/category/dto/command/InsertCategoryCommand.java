package org.svetso.marketplace_monolyth.product.application.category.dto.command;

import java.util.List;

public record InsertCategoryCommand(
        Long requesterId,
        Long categoryToInsertId,
        List<Long> categoriesId
) {}
