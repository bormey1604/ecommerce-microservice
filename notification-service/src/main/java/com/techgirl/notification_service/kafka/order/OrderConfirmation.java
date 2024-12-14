package com.techgirl.notification_service.kafka.order;

import com.techgirl.notification_service.kafka.payment.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        double totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
){
}
