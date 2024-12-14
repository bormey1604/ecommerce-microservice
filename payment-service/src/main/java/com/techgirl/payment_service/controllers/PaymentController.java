package com.techgirl.payment_service.controllers;

import com.techgirl.payment_service.models.requests.PaymentRequest;
import com.techgirl.payment_service.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody @Valid PaymentRequest request){
        return new ResponseEntity<>(paymentService.createPayment(request), HttpStatus.OK);
    }
}
