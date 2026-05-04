package org.svetso.marketplace_monolyth.product.infrastructure.presistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
import org.svetso.marketplace_monolyth.product.application.category.mapper.CategoryDtoMapper;
import org.svetso.marketplace_monolyth.product.application.category.port.in.GetCategoryUseCase;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Category;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.infrastructure.mapper.ProductMapper;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.ProductEntity;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaProductRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;
    private final GetCategoryUseCase getCategoryUseCase;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public Product findById(Long id) {
        return productMapper.toDomain(jpaProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found by id")));
    }

    @Override
    public List<Product> findBySellerIdAndSellerType(Long sellerId, SellerType sellerType) {
        return jpaProductRepository.findBySellerIdAndSellerType(sellerId, sellerType)
                .orElseThrow(() -> new NotFoundException("Seller does not have products"))
                .stream()
                .map(productMapper::toDomain)
                .toList();
    }

    @Override
    public Product save(Product product) {
        Category category = categoryDtoMapper.dtoToCategory(getCategoryUseCase.execute(product.getCategoryId()));
        return productMapper.toDomain(jpaProductRepository.save(productMapper.toEntity(product, category)));
    }

    @Override
    public void delete(Product product) {
        ProductEntity entity = productMapper.toEntity(
                product,
                categoryDtoMapper.dtoToCategory(getCategoryUseCase.execute(product.getCategoryId())));
        jpaProductRepository.delete(entity);
    }

    @Override
    public Page<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return jpaProductRepository.findAll(pageable)
                .map(productMapper::toDomain);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public List<Product> findByCategoryIds(List<Long> categoryIds) {
        return jpaProductRepository.findByCategoryIdIn(categoryIds)
                .stream()
                .map(productMapper::toDomain)
                .toList();
    }
}
