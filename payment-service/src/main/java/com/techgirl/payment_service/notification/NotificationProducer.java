package com.techgirl.payment_service.notification;

import com.techgirl.payment_service.models.requests.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public void sendNotification(String paymentNotificationRequest) {
        log.info("Sending notification request: {}", paymentNotificationRequest);

        kafkaTemplate.send(topic,paymentNotificationRequest);
    }
}
