package com.techgirl.notification_service.kafka;

import com.google.gson.Gson;
import com.techgirl.notification_service.kafka.order.OrderConfirmation;
import com.techgirl.notification_service.kafka.payment.PaymentConfirmation;
import com.techgirl.notification_service.models.Notification;
import com.techgirl.notification_service.models.NotificationType;
import com.techgirl.notification_service.repository.NotificationRepository;
import com.techgirl.notification_service.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.payment.topic}", groupId = "${spring.kafka.payment.group-id}")
    public void consumePaymentSuccessNotification(String message) throws MessagingException {
        System.out.println("Consuming message from payment-topic"+ message);

        Gson gson = new Gson();
        PaymentConfirmation paymentConfirmation = gson.fromJson(message, PaymentConfirmation.class);

        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = "${spring.kafka.order.topic}", groupId = "${spring.kafka.order.group-id}")
    public void orderConfirmation(String message) throws MessagingException {
        System.out.println("Consuming message from order-topic"+ message);

        Gson gson = new Gson();
        OrderConfirmation orderConfirmation = gson.fromJson(message, OrderConfirmation.class);

        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

}
