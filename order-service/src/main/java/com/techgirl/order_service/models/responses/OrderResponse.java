package com.techgirl.order_service.models.responses;

import com.techgirl.order_service.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private String reference;
    private double amount;
    private PaymentMethod paymentMethod;
    private String customerId;
}
