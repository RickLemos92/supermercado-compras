package com.example.backend.service.impl;

import com.example.backend.model.CustomerOrder;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public CustomerOrder createOrder(CustomerOrder order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
