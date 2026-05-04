package org.svetso.marketplace_monolyth.product.application.category.port.in;

import java.util.List;

public interface GetDescendantCategoriesIdUseCase {
    List<Long> execute(Long categoryId);
}
