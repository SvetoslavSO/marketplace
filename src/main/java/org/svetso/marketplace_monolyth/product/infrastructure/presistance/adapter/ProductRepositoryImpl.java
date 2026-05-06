package org.svetso.marketplace_monolyth.product.infrastructure.presistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
import org.svetso.marketplace_monolyth.product.application.product.port.out.ProductRepository;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;
import org.svetso.marketplace_monolyth.product.infrastructure.mapper.ProductMapper;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.CategoryEntity;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.entity.ProductEntity;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaCategoryRepository;
import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaProductRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product findById(Long id) {
        return productMapper.toDomain(jpaProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found by id")));
    }

    @Override
    public Page<Product> findBySellerIdAndSellerType(Long sellerId, SellerType sellerType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaProductRepository.findBySellerIdAndSellerType(sellerId, sellerType, pageable)
                .map(productMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        CategoryEntity categoryRef = jpaCategoryRepository.getReferenceById(product.getCategoryId());
        ProductEntity entity = productMapper.toEntity(product, categoryRef);
        return productMapper.toDomain(jpaProductRepository.save(entity));
    }

    @Override
    public void delete(Product product) {
        CategoryEntity categoryRef = jpaCategoryRepository.getReferenceById(product.getCategoryId());
        ProductEntity entity = productMapper.toEntity(product, categoryRef);
        jpaProductRepository.delete(entity);
    }

    @Override
    public Page<Product> findByCategoryIds(List<Long> categoryIds, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaProductRepository.findByCategoryIdIn(categoryIds, pageable)
                .map(productMapper::toDomain);
    }

    @Override
    public Page<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaProductRepository.findAll(pageable).map(productMapper::toDomain);
    }
}
