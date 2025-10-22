package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;


public record CreateOrderDto(@NotBlank String merchantId,
                             @NotBlank Long amount,
                             @NotBlank String currency) {
}
