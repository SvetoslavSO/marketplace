package org.svetso.marketplace_monolyth.product.web.dto.request;

import java.util.List;

public record InsertCategoryRequest(
        String name,
        Long parentId,
        List<Long> childrenIds
) {}