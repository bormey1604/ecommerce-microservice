package com.techgirl.order_service.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Integer productId;

    @Positive(message = "Quantity is mandatory")
    private double quantity;
}
