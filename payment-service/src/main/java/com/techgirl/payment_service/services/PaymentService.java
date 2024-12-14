package com.techgirl.payment_service.services;

import com.techgirl.payment_service.models.requests.PaymentNotificationRequest;
import com.techgirl.payment_service.models.requests.PaymentRequest;
import com.techgirl.payment_service.notification.NotificationProducer;
import com.techgirl.payment_service.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepositoy;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment( PaymentRequest request) {

        var payment = paymentRepositoy.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstname(),
                        request.getCustomer().getLastname(),
                        request.getCustomer().getEmail()
                ).toJson()
        );
        return payment.getId();
    }
}
