package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.dto.command.InsertCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.application.category.mapper.CategoryDtoMapper;
import org.svetso.marketplace_monolyth.product.application.category.port.in.InsertBetweenCategoriesUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InsertBetweenCategoriesService implements InsertBetweenCategoriesUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public CategoryDto execute(InsertCategoryCommand command) {
        Category categoryToInsert = categoryRepository.findById(command.categoryToInsertId());

        List<Category> categories = command.categoriesId().stream()
                .map(categoryRepository::findById)
                .toList();

        Long parentId = categories.getFirst().getParentId();
        categoryToInsert.setParentId(parentId);

        Category saved = categoryRepository.save(categoryToInsert);

        for (Category category : categories) {
            category.setParentId(saved.getId());
            categoryRepository.save(category);
        }

        return categoryDtoMapper.categoryToDto(categoryToInsert);
    }
}
