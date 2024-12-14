package com.techgirl.order_service.services;

import com.techgirl.order_service.models.Order;
import com.techgirl.order_service.models.requests.OrderRequest;
import com.techgirl.order_service.models.responses.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .reference(request.getReference())
                .totalAmount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
