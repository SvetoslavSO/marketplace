package org.svetso.marketplace_monolyth.product.application.category.port.out;


import org.svetso.marketplace_monolyth.product.domain.model.Category;

public interface CategoryRepository {
    Category findById(Long id);
    Category findByName(String name);
    Category save(Category category);
}
