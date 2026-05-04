package org.svetso.marketplace_monolyth.product.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.product.application.category.port.in.*;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.web.dto.request.CategoryRequest;
import org.svetso.marketplace_monolyth.product.web.dto.request.CreateCategoryRequest;
import org.svetso.marketplace_monolyth.product.web.dto.request.UpdateProductRequest;
import org.svetso.marketplace_monolyth.product.web.dto.response.CategoryResponse;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductResponse;
import org.svetso.marketplace_monolyth.product.web.mapper.WebRequestCategoryMapper;
import org.svetso.marketplace_monolyth.product.web.mapper.WebResponseCategoryMapper;
import org.svetso.marketplace_monolyth.security.AuthContext;
import org.svetso.marketplace_monolyth.security.CurrentUser;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final AddCategoryUseCase addCategoryUseCase;
//    private final DeleteCategoryUseCase deleteCategoryUseCase;  //todo

    private final GetCategoryUseCase getCategoryUseCase;
    private final InsertBetweenCategoriesUseCase insertBetweenCategoriesUseCase;
    private final ListCategoryTreeUseCase listCategoryTreeUseCase;
    private final ListParentCategoriesUseCase listParentCategoriesUseCase;
    private final WebRequestCategoryMapper webRequestCategoryMapper;
    private final WebResponseCategoryMapper webResponseCategoryMapper;
    private final AuthContext authContext;

    // создать категорию
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CreateCategoryRequest request) {
        Long userId = authContext.getCurrentUser().userId();
        return webResponseCategoryMapper.dtoToResponse(
                addCategoryUseCase.execute(
                        webRequestCategoryMapper.createCategoryRequestToCommand(userId ,request)
                )
        );
    }

    // Получить категорию по id
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return webResponseCategoryMapper.dtoToResponse(getCategoryUseCase.execute(id));
    }

    // Получить все родительские категории
    @GetMapping("/relative/{id}")
    public List<CategoryResponse> getRelativeCategories(@PathVariable Long id){
        return listParentCategoriesUseCase.execute(id)
                .stream()
                .map(webResponseCategoryMapper::dtoToResponse)
                .toList();
    }

    @GetMapping("/tree")
    public List<CategoryResponse> getCategoriesTree() {
        return listCategoryTreeUseCase.execute()
                .stream()
                .map(webResponseCategoryMapper::dtoToResponse)
                .toList();
    }

    @PutMapping("/insertCategory/{id}")
    public CategoryResponse insertCategory(@PathVariable Long id, List<Long> categoriesIdList) {
        Long userId = authContext.getCurrentUser().userId();

        return webResponseCategoryMapper.dtoToResponse(
                insertBetweenCategoriesUseCase.execute(
                    webRequestCategoryMapper.requestToInsertCategoryCommand(userId, id, categoriesIdList)
                )
        );
    }


}
