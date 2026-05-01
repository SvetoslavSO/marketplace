//package org.svetso.marketplace_monolyth.auth.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.svetso.marketplace_monolyth.BaseEntity;
//import org.svetso.marketplace_monolyth.product.entity.Product;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "order_items")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class OrderItem extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//    @Column(name = "unit_price", precision = 10, scale = 2)
//    private BigDecimal unitPrice;
//}
