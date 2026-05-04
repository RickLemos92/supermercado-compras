package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order")
@Data
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String address;
    private String paymentMethod;
    private Double totalAmount;
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
