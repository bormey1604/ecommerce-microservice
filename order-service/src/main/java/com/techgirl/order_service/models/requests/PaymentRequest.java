package com.techgirl.order_service.models.requests;

import com.techgirl.order_service.models.responses.CustomerResponse;
import com.techgirl.order_service.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

//    private Integer id;
    private double amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private CustomerResponse customer;

}
