package com.example.demo.controller;

import com.example.demo.model.CreateOrderDto;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/create/order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        orderService.createOrder(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
