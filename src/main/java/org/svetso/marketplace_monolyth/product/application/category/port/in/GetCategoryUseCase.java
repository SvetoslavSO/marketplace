package org.svetso.marketplace_monolyth.product.application.category.port.in;

import org.svetso.marketplace_monolyth.product.domain.model.Category;

public interface GetCategoryUseCase {
    Category execute(Long id);
}
