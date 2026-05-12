package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
    @NotBlank(message = "O nome do produto é obrigatório")
    String productName,
    
    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser positiva")
    Integer quantity,
    
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    Double price
) {}
