package com.prince.product_service.product_service.dto;


import com.prince.product_service.product_service.entity.Product;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @NotNull(message = "Category cannot be null")
    private Product.Category category;
}
