package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.dto.command.AddCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.application.category.mapper.CategoryDtoMapper;
import org.svetso.marketplace_monolyth.product.application.category.port.in.AddCategoryUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AddCategoryService implements AddCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public CategoryDto execute(AddCategoryCommand command) {
        Category category = categoryRepository.save(new Category(
                null,
                command.name(),
                command.parentId())
        );

        return categoryDtoMapper.categoryToDto(category);
    }
}
