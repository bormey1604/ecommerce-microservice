package com.techgirl.payment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KakaPaymentTopicConfig {

    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("payment-topic", 1, (short) 1);
    }
}
