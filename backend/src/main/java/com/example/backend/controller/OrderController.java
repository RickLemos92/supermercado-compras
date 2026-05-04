package com.example.backend.controller;

import com.example.backend.model.CustomerOrder;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
