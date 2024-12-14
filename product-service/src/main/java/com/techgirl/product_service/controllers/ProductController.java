package com.techgirl.product_service.controllers;


import com.techgirl.product_service.models.request.ProductPurchaseRequest;
import com.techgirl.product_service.models.request.ProductRequest;
import com.techgirl.product_service.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> purchaseRequest) {
        return new ResponseEntity<>(productService.purchaseProducts(purchaseRequest), HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<?> findById(@PathVariable("product_id") Integer productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

}
