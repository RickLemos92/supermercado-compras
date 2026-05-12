package com.example.backend.service;

import com.example.backend.model.CustomerOrder;
import java.util.List;

public interface OrderService {
    CustomerOrder createOrder(CustomerOrder order);
    List<CustomerOrder> findAll();
    CustomerOrder findById(Long id);
}
