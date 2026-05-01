//package org.svetso.marketplace_monolyth.product.services;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.svetso.marketplace_monolyth.product.entity.Category;
//import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
//import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
//import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
//import org.svetso.marketplace_monolyth.product.dto.CreateProductDTO;
//import org.svetso.marketplace_monolyth.product.dto.ProductDTO;
//import org.svetso.marketplace_monolyth.product.dto.UpdateProductDTO;
//import org.svetso.marketplace_monolyth.product.entity.Product;
//import org.svetso.marketplace_monolyth.user.entity.User;
//import org.svetso.marketplace_monolyth.user.repository.UserRepository;
//import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaCategoryRepository;
//import org.svetso.marketplace_monolyth.product.infrastructure.presistance.repository.JpaProductRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class ProductService {
//
//    private final UserRepository userRepository;
//    private final JpaProductRepository jpaProductRepository;
//    private final JpaCategoryRepository jpaCategoryRepository;
//
//
//    public void deleteProduct(Long productId, Long userId) {
//        log.info("User {} attempts delete product {}", userId, productId);
//        Product product = jpaProductRepository.findById(productId)
//                .orElseThrow(() -> new BadRequestException("Delete product fail: Product not found"));
//
//        if (!product.getUser().getId().equals(userId)) {
//            throw new ForbiddenException("Delete product fail: Not your product");
//        }
//
//        jpaProductRepository.delete(product);
//    }
//
//    public List<ProductDTO> getProductsByUser(Long id) {
//        List<Product> products = jpaProductRepository.findByUserId(id);
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        for (Product product : products) {
//            productDTOS.add(productConverter(product));
//        }
//        return productDTOS;
//    }
//
//    public ProductDTO getProduct(Long productId) {
//        log.info("Attempt to get product with id {}", productId);
//        Product product =  jpaProductRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("Get product fail: Product not found"));
//        return productConverter(product);
//    }
//
//    public List<ProductDTO> getAllProducts() {
//        List<Product> products = jpaProductRepository.findAll();
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        for (Product product : products) {
//            productDTOS.add(productConverter(product));
//        }
//        return productDTOS;
//    }
//
//    public ProductDTO updateProduct(Long id, UpdateProductDTO productDTO, Long userId) {
//        log.info("User {} attempts update product {}", userId, productDTO.getName());
//        Product product = jpaProductRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Update fail: product not found"));
//        if (!product.getUser().getId().equals(userId)) {
//            throw new ForbiddenException("Update fail: Not your product");
//        }
//
//        if (productDTO.getName() != null) {
//            product.setName(productDTO.getName());
//        }
//
//        if (productDTO.getDescription() != null) {
//            product.setDescription(productDTO.getDescription());
//        }
//
//        if (productDTO.getPrice() != null) {
//            product.setPrice(productDTO.getPrice());
//        }
//
//        if (productDTO.getStock() != null) {
//            product.setStock(productDTO.getStock());
//        }
//
//        if (productDTO.getCategoryId() != null) {
//            Category category = jpaCategoryRepository.findById(productDTO.getCategoryId())
//                    .orElseThrow(() -> new NotFoundException("Update fail: Category not found"));
//            product.setCategory(category);
//        }
//
//        jpaProductRepository.save(product);
//        log.info("User {} updates product {}", userId, product.getName());
//        return productConverter(product);
//    }
//
//
//
//    public ProductDTO createProduct(CreateProductDTO request, Long userId) {
//        log.info("User {} attempts create product {}", userId, request.getName());
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("Create product fail: User not found"));
//
//        Product product = new Product();
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        Category category = jpaCategoryRepository.findById(request.getCategoryId())
//                .orElseThrow(() -> new NotFoundException("Create product fail: Category not found"));
//        product.setCategory(category);
//        product.setUser(user);
//        product.setStock(request.getStock());
//        Product newProduct = jpaProductRepository.save(product);
//        log.info("User {} creates product {}", userId, request.getName());
//        return productConverter(newProduct);
//    }
//
//    private ProductDTO productConverter(Product product) {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setCategoryId(product.getCategory().getId());
//        productDTO.setId(product.getId());
//        productDTO.setDescription(product.getDescription());
//        productDTO.setName(product.getName());
//        productDTO.setStock(product.getStock());
//        productDTO.setPrice(product.getPrice());
//        productDTO.setUpdatedAt(product.getUpdatedAt());
//        return productDTO;
//    }
//}
