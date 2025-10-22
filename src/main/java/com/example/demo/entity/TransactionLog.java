package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "TransactionLog")
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionLogId;

    private Long paymentId;

    private String eventType;

    private LocalDateTime timeStampTransaction;
}
