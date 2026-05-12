package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record OrderRequest(
    @NotBlank(message = "O endereço é obrigatório")
    String address,
    
    @NotBlank(message = "O método de pagamento é obrigatório")
    String paymentMethod,
    
    @NotNull(message = "O valor total é obrigatório")
    @Positive(message = "O valor total deve ser positivo")
    Double totalAmount,
    
    @NotEmpty(message = "O pedido deve ter pelo menos um item")
    List<OrderItemRequest> items
) {}
