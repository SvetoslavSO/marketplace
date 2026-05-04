package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.port.in.GetDescendantCategoriesIdUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetDescendantCategoriesIdService implements GetDescendantCategoriesIdUseCase {

    private final CategoryRepository categoryRepository;
    @Override
    public List<Long> execute(Long categoryId) {
        return categoryRepository.findAllDescendantIds(categoryId);
    }
}
