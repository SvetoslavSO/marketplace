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
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "payments")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Payment extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;
//
//    @Column(name = "amount", precision = 10, scale = 2)
//    private BigDecimal amount;
//
//    @Column(name = "payment_method")
//    private String paymentMethod;
//
//    @Column(name = "status")
//    private String status;
//
//    @Column(name = "paid_at")
//    private LocalDateTime paidAt;
//}
