package org.svetso.marketplace_monolyth.product.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long categoryId;
    private Long sellerId;
    private SellerType sellerType;

    public Product(Long id, String name, String description, BigDecimal price, int stock, Long categoryId, Long sellerId, SellerType sellerType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.sellerType = sellerType;
    }

    public void updateDetails(String name, String description, BigDecimal price, int stock, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public SellerType getSellerType() {
        return sellerType;
    }
}
