package com.techgirl.order_service.services;

import com.techgirl.order_service.clients.payment.PaymentClient;
import com.techgirl.order_service.clients.product.ProductClient;
import com.techgirl.order_service.exception.BusinessException;
import com.techgirl.order_service.clients.customer.CustomerClient;
import com.techgirl.order_service.kafka.OrderConfirmation;
import com.techgirl.order_service.kafka.OrderProducer;
import com.techgirl.order_service.models.requests.OrderLineRequest;
import com.techgirl.order_service.models.requests.OrderRequest;
import com.techgirl.order_service.models.requests.PaymentRequest;
import com.techgirl.order_service.models.requests.PurchaseRequest;
import com.techgirl.order_service.models.responses.OrderResponse;
import com.techgirl.order_service.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {

        var customer = this.customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exist with the provided ID"));

        var purchaseProducts = this.productClient.purchaseProducts(request.getProducts());

        //persist order
        var order = this.orderRepository.save(mapper.toOrder(request));

        //persist order lines
        for(PurchaseRequest purchaseRequest: request.getProducts()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null,order.getId(),purchaseRequest.getProductId(),purchaseRequest.getQuantity())
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.getAmount(),
                request.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation --> notification-ms
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        customer,
                        purchaseProducts
                ).toJson()
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No Order exist with the provided ID::" +orderId));
    }
}
