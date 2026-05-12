package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message = "O nome é obrigatório")
    String name,
    
    @NotBlank(message = "A descrição é obrigatória")
    String description,
    
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    Double price,
    
    @NotBlank(message = "A URL da imagem é obrigatória")
    String imageUrl,
    
    @NotBlank(message = "A categoria é obrigatória")
    String category
) {}
