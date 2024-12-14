package com.techgirl.customer_service.service;

import com.techgirl.customer_service.model.Customer;
import com.techgirl.customer_service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        var updateCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if(StringUtils.isNotBlank(customer.getFirstName())) {
            updateCustomer.setFirstName(customer.getFirstName());
        }

        if(StringUtils.isNotBlank(customer.getLastName())) {
            updateCustomer.setLastName(customer.getLastName());
        }

        if(StringUtils.isNotBlank(customer.getEmail())) {
            updateCustomer.setLastName(customer.getEmail());
        }

        if(customer.getAddress() != null) {
            updateCustomer.setAddress(customer.getAddress());
        }

        customerRepository.save(updateCustomer);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
