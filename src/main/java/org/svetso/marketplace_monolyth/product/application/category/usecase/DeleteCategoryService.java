package org.svetso.marketplace_monolyth.product.application.category.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.product.application.category.dto.command.DeleteCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.port.in.DeleteCategoryUseCase;
import org.svetso.marketplace_monolyth.product.application.category.port.out.CategoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteCategoryService implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public void execute(DeleteCategoryCommand command) {

    }
}
