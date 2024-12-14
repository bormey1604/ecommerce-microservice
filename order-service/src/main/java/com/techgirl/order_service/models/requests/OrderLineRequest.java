package com.techgirl.order_service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private double quantity;
}
