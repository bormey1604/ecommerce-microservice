package com.techgirl.payment_service.services;

import com.techgirl.payment_service.models.Payment;
import com.techgirl.payment_service.models.requests.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.getId())
                .orderId(request.getOrderId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .build();
    }
}
