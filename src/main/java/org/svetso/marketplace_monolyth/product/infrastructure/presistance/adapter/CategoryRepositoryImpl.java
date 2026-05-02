package org.svetso.marketplace_monolyth.product.infrastructure.presistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;
import org.svetso.marketplace_monolyth.product.infrastructure.mapper.CategoryMapper;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaCategoryRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category findById(Long id) {
        return categoryMapper.toDomain(
                jpaCategoryRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Category not found by id"))
        );
    }

    @Override
    public Category findByName(String name) {
        return categoryMapper.toDomain(
                jpaCategoryRepository.findByName(name)
                        .orElseThrow(() -> new NotFoundException("Category not found by name"))
        );
    }

    @Override
    public Category save(Category category) {
        if (category.getId() == null) {
            CategoryEntity entity = categoryMapper.toEntity(category);
            return categoryMapper.toDomain(jpaCategoryRepository.save(entity));
        }

        CategoryEntity entity = jpaCategoryRepository.findById(category.getId())
                .orElseThrow(() -> new NotFoundException("category not found"));
        entity.setName(category.getName());
        CategoryEntity parentEntity = new CategoryEntity();
        parentEntity.setId(category.getParentId());
        entity.setParent(parentEntity);
        return categoryMapper.toDomain(entity);
    }

    @Override
    public void delete(Category category) {
        CategoryEntity entity = jpaCategoryRepository.findById(category.getId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        CategoryEntity parentEntity = null;

        if (entity.getParent() != null) {
            parentEntity = entity.getParent();
        }

        List<CategoryEntity> childEntities = jpaCategoryRepository.findCategoriesByParentId(category.getId());

        for (CategoryEntity childEntity: childEntities) {
            childEntity.setParent(parentEntity);
        }

        jpaCategoryRepository.delete(entity);
    }
}
