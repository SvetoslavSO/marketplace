package org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(Long id);
    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findCategoriesByParentId(Long parentId);
    @Query(value = """
        WITH RECURSIVE subcategories AS (
            SELECT id
            FROM categories
            WHERE id = :categoryId

            UNION ALL

            SELECT c.id
            FROM categories c
            JOIN subcategories s ON c.parent_id = s.id
        )
        SELECT id FROM subcategories
        """, nativeQuery = true)
    List<Long> findAllDescendantIds(Long categoryId);
    @Query(value = """
    WITH RECURSIVE category_tree AS (
        SELECT * FROM categories WHERE id = :categoryId
        UNION ALL
        SELECT c.* FROM categories c
        JOIN category_tree ct ON ct.parent_id = c.id
    )
    SELECT * FROM category_tree
    """, nativeQuery = true)
    List<CategoryEntity> findAllParents(Long categoryId);
    @Query(value = """
    WITH RECURSIVE category_tree AS (
        SELECT * FROM categories WHERE parent_id is NULL
        UNION ALL
        SELECT c.* FROM categories c
        JOIN category_tree ct ON c.parent_id = ct.id
    )
    SELECT * FROM category_tree
    """, nativeQuery = true)
    List<CategoryEntity> findAllTree();
}
