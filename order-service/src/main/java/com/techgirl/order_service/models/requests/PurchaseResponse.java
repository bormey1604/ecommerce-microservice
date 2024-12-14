package com.techgirl.order_service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private Integer productId;
    private String name;
    private String description;
    private double price;
    private double quantity;
}
