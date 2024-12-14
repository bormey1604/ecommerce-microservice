package com.techgirl.order_service.controllers;

import com.techgirl.order_service.models.requests.OrderRequest;
import com.techgirl.order_service.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest request){
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<?> findById(@PathVariable("order-id") Integer orderId){
        return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
    }
}
