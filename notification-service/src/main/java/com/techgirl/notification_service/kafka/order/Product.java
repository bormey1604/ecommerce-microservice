package com.techgirl.notification_service.kafka.order;

public record Product(
        Integer productId,
        String name,
        String description,
        double price,
        double quantity
) {
}
