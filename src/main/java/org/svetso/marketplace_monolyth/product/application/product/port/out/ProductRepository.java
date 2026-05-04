package org.svetso.marketplace_monolyth.product.application.product.port.out;

import org.springframework.data.domain.Page;
import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findBySellerIdAndSellerType(Long sellerId, SellerType sellerType);
    Product save(Product product);
    void delete(Product product);
    Page<Product> findAll(int page, int size);
    List<Product> findProductsByCategoryId(Long categoryId);
    List<Product> findByCategoryIds(List<Long> categoryIds);
}
