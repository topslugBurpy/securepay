package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Order")
@Table(name = "orders")
public class Order {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    //mapping of order to payments
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    private List<Payment> payments;

    private String merchantId;

    private Long amount;

    private String currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}

