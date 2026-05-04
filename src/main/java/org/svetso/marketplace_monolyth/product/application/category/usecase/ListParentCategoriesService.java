package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.dto.response.CategoryDto;
import org.svetso.marketplace_monolyth.product.application.category.mapper.CategoryDtoMapper;
import org.svetso.marketplace_monolyth.product.application.category.port.in.ListParentCategoriesUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ListParentCategoriesService implements ListParentCategoriesUseCase {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public List<CategoryDto> execute(Long id) {
        return categoryRepository.findAllParents(id)
                .stream()
                .map(categoryDtoMapper::categoryToDto)
                .toList();
    }
}
