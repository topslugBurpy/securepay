package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Payment")
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    //mapping of payment to paymentAttempts
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payment", orphanRemoval = true)
    private List<PaymentAttempt> paymentAttempts;

    private String merchantId;

    private Long amount;

    private String username;

    private String status;

    private LocalDateTime timeStamp;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}

