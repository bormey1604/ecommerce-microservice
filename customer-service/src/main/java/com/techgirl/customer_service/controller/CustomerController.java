package com.techgirl.customer_service.controller;

import com.techgirl.customer_service.model.Customer;
import com.techgirl.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer customer) {
        customerService.updateCustomer(customer);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<?> getCustomer(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
