package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.application.category.mapper.CategoryDtoMapper;
import org.svetso.marketplace_monolyth.product.application.category.port.in.ListCategoryTreeUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListCategoryTreeUseService implements ListCategoryTreeUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public List<CategoryDto> execute() {
        return categoryRepository.getCategoryTree()
                .stream()
                .map(categoryDtoMapper::categoryToDto)
                .toList();
    }
}
