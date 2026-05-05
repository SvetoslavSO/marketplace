package org.svetso.marketplace_monolyth.product.application.category.dto.command;

import java.util.List;

public record InsertCategoryCommand(
        Long requesterId,
        String name,
        Long parentId,
        List<Long> categoriesId
) {}
