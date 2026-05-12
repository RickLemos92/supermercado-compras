package com.example.backend.dto;

public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price,
    String imageUrl,
    String category
) {}
