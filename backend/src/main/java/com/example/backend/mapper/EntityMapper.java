package com.example.backend.mapper;

import com.example.backend.dto.*;
import com.example.backend.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityMapper {

    public ProductResponse toResponse(Product product) {
        if (product == null) return null;
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImageUrl(),
            product.getCategory()
        );
    }

    public Product toEntity(ProductRequest request) {
        if (request == null) return null;
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setImageUrl(request.imageUrl());
        product.setCategory(request.category());
        return product;
    }

    public CustomerOrder toEntity(OrderRequest request) {
        if (request == null) return null;
        CustomerOrder order = new CustomerOrder();
        order.setAddress(request.address());
        order.setPaymentMethod(request.paymentMethod());
        order.setTotalAmount(request.totalAmount());
        order.setItems(request.items().stream()
            .map(this::toEntity)
            .collect(Collectors.toList()));
        return order;
    }

    public OrderItem toEntity(OrderItemRequest request) {
        if (request == null) return null;
        OrderItem item = new OrderItem();
        item.setProductName(request.productName());
        item.setQuantity(request.quantity());
        item.setPrice(request.price());
        return item;
    }
}
