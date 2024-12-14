package com.techgirl.notification_service.kafka.payment;

public record PaymentConfirmation(
    String orderReference,
    double amount,
    PaymentMethod paymentMethod,
    String customerFirstname,
    String customerLastname,
    String customerEmail
){
}
