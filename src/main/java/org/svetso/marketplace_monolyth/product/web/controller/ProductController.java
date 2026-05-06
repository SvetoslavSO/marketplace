package org.svetso.marketplace_monolyth.product.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.product.application.product.dto.command.CreateProductCommand;
import org.svetso.marketplace_monolyth.product.application.product.port.in.*;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.web.dto.request.CreateProductRequest;
import org.svetso.marketplace_monolyth.product.web.dto.request.UpdateProductRequest;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductPageResponse;
import org.svetso.marketplace_monolyth.product.web.dto.response.ProductResponse;
import org.svetso.marketplace_monolyth.product.web.mapper.WebRequestProductMapper;
import org.svetso.marketplace_monolyth.product.web.mapper.WebResponseProductMapper;
import org.svetso.marketplace_monolyth.security.AuthContext;
import org.svetso.marketplace_monolyth.security.CurrentUser;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final AuthContext authContext;
    private final WebRequestProductMapper requestMapper;
    private final WebResponseProductMapper responseMapper;
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final ListProductsBySellerUseCase listProductsBySellerUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ListProductsByCategoryUseCase listProductsByCategoryUseCase;

    // Создание товара
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        CurrentUser user = authContext.getCurrentUser();

        CreateProductCommand command =
                requestMapper.createRequestToCommand(user.userId(), request);

        return responseMapper.productDtoToResponse(
                createProductUseCase.execute(command)
        );
    }

    // Получить товар по id
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return responseMapper.productDtoToResponse(
                getProductUseCase.execute(id)
        );
    }

    // Получить товары по категории (включая потомков)
    @GetMapping("/category/{categoryId}")
    public ProductPageResponse getByCategory(
            @PathVariable Long categoryId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return responseMapper.pageDtoToResponse(
                listProductsByCategoryUseCase.execute(
                        categoryId,
                        page,
                        size
                )
        );
    }

    //  Получить товары продавца
    @GetMapping("/seller/{sellerId}")
    public ProductPageResponse getBySeller(
            @PathVariable Long sellerId,
            @RequestParam SellerType sellerType,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return responseMapper.pageDtoToResponse(
                listProductsBySellerUseCase.execute(
                        requestMapper.sellerProductsCommand(sellerId, sellerType),
                        page,
                        size
                )
        );
    }

    @GetMapping("/me")
    public ProductPageResponse getMyProducts(
            @RequestParam int page,
            @RequestParam int size
    ) {
        CurrentUser user = authContext.getCurrentUser();

        return responseMapper.pageDtoToResponse(
                listProductsBySellerUseCase.execute(
                        requestMapper.sellerProductsCommand(user.userId(), SellerType.USER),
                        page,
                        size
                )
        );
    }

    // Обновление товара
    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        CurrentUser user = authContext.getCurrentUser();

        return responseMapper.productDtoToResponse(
                updateProductUseCase.execute(
                        requestMapper.updateProductCommand(user.userId(), id, request)
                )
        );
    }

    // Удаление товара
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        CurrentUser user = authContext.getCurrentUser();

        deleteProductUseCase.execute(
                requestMapper.deleteProductCommand(user.userId(), id)
        );
    }

    @GetMapping("page/{page}/{size}")
    public ProductPageResponse getPage(@PathVariable int page, @PathVariable int size) {
        return responseMapper.pageDtoToResponse(listProductsUseCase.execute(page, size));
    }
}
