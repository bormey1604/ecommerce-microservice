package com.techgirl.payment_service.models.requests;

import com.techgirl.payment_service.models.Customer;
import com.techgirl.payment_service.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Integer id;
    private double amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private Customer customer;


}
