package com.techgirl.order_service.services;

import com.techgirl.order_service.models.Order;
import com.techgirl.order_service.models.OrderLine;
import com.techgirl.order_service.models.requests.OrderLineRequest;
import com.techgirl.order_service.models.responses.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.getId())
                .quantity(request.getQuantity())
                .order(
                        Order.builder()
                                .id(request.getOrderId())
                                .build()
                )
                .productId(request.getProductId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
