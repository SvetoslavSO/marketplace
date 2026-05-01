package org.svetso.marketplace_monolyth.product.domain.model;

public class Seller {
    private Long id;
    private Long sellerId;
    private SellerType sellerType;

    public Seller(Long id, Long sellerId, SellerType sellerType) {
        this.id = id;
        this.sellerId = sellerId;
        this.sellerType = sellerType;
    }

    public Long getId() {
        return id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public SellerType getSellerType() {
        return sellerType;
    }
}
