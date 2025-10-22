package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.model.CreateOrderDto;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public Order createOrder(CreateOrderDto createOrderDto) {
        var order = new Order();
        order.setAmount(createOrderDto.amount());
        order.setCurrency(createOrderDto.currency());
        order.setMerchantId(createOrderDto.merchantId());

        order.setOrderStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        //save to db
        orderRepository.save(order);

        return order;
    }
}
