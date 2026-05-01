package org.svetso.marketplace_monolyth.product.application.product.port.out;

import org.svetso.marketplace_monolyth.product.domain.model.Product;
import org.svetso.marketplace_monolyth.product.domain.model.SellerType;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findBySellerIdAndSellerType(Long sellerId, SellerType sellerType);
    Product save(Product product);
}
