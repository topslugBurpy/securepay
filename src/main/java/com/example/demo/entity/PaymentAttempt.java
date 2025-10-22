package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "PaymentAttempt")
@Table(name = "payment_attempts")
public class PaymentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentAttemptId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    private Integer attemptNumber;

    private LocalDateTime timeStampPaymentAttempt;

    @Enumerated(EnumType.STRING)
    private AttemptStatus attemptStatus;
}


