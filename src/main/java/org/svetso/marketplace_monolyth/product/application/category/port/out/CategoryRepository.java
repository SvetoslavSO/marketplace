package org.svetso.marketplace_monolyth.product.application.category.port.out;


import org.svetso.marketplace_monolyth.product.domain.model.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(Long id);
    Category findByName(String name);
    Category save(Category category);
    void delete(Category category);
    List<Long> findAllDescendantIds(Long categoryId);
    List<Category> findAllParents(Long categoryId);
    List<Category> getCategoryTree();
}
