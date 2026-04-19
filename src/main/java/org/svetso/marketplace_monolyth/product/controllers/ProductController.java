package org.svetso.marketplace_monolyth.product.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.product.dto.CreateProductDTO;
import org.svetso.marketplace_monolyth.product.dto.ProductDTO;
import org.svetso.marketplace_monolyth.product.dto.UpdateProductDTO;
import org.svetso.marketplace_monolyth.product.services.ProductService;
import org.svetso.marketplace_monolyth.security.AuthContext;
import org.svetso.marketplace_monolyth.security.CurrentUser;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final AuthContext authContext;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user')")
    public ProductDTO create(@Valid @RequestBody CreateProductDTO request) {
        CurrentUser user = authContext.getCurrentUser();
        return productService.createProduct(request, user.userId());
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('user')")
    public List<ProductDTO> getMyProducts() {
        CurrentUser user = authContext.getCurrentUser();
        return productService.getProductsByUser(user.userId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public void deleteProduct(@PathVariable Long id) {
        CurrentUser user = authContext.getCurrentUser();
        productService.deleteProduct(id, user.userId());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ProductDTO updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductDTO newProduct
    ) {
        CurrentUser user = authContext.getCurrentUser();
        return productService.updateProduct(id, newProduct, user.userId());
    }
}
