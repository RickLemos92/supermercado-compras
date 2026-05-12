package com.example.backend.controller;

import com.example.backend.dto.OrderRequest;
import com.example.backend.mapper.EntityMapper;
import com.example.backend.model.CustomerOrder;
import com.example.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityMapper mapper;

    @PostMapping
    public ResponseEntity<CustomerOrder> createOrder(@Valid @RequestBody OrderRequest request) {
        CustomerOrder order = mapper.toEntity(request);
        CustomerOrder savedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}
