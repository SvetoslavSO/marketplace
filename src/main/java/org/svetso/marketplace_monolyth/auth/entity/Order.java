//package org.svetso.marketplace_monolyth.auth.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.svetso.marketplace_monolyth.BaseEntity;
//import org.svetso.marketplace_monolyth.user.entity.User;
//
//import java.math.BigDecimal;
//
//
//@Entity
//@Table(name = "orders")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Order extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id")
//    private Address address;
//
//    private String status;
//
//    private BigDecimal totalAmount;
//}