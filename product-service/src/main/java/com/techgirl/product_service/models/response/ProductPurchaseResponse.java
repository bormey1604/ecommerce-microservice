package com.techgirl.product_service.models.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class ProductPurchaseResponse {
    private Integer productId;
    private String name;
    private String description;
    private double price;
    private double quantity;
}
