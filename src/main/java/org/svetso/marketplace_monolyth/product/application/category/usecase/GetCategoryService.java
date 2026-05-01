package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.port.in.GetCategoryUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GetCategoryService implements GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category execute(Long id) {
        return categoryRepository.findById(id);
    }
}
