package org.svetso.marketplace_monolyth.product.web.mapper;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.product.application.category.dto.command.AddCategoryCommand;
import org.svetso.marketplace_monolyth.product.application.category.dto.command.InsertCategoryCommand;
import org.svetso.marketplace_monolyth.product.web.dto.request.CategoryRequest;
import org.svetso.marketplace_monolyth.product.web.dto.request.CreateCategoryRequest;

import java.util.List;

@Component
public class WebRequestCategoryMapper {

    public AddCategoryCommand createCategoryRequestToCommand(Long userId, CreateCategoryRequest request) {
        return new AddCategoryCommand(
                userId,
                request.name(),
                request.parentId()
        );
    }

    public InsertCategoryCommand requestToInsertCategoryCommand(Long userId, Long id, List<Long> categoriesIdsList) {
        return new InsertCategoryCommand(
                userId,
                id,
                categoriesIdsList
        );
    }
}
