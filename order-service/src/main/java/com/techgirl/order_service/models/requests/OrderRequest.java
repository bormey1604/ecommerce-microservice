package com.techgirl.order_service.models.requests;


import com.techgirl.order_service.models.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Integer id;
    private String reference;
    @Positive(message = "Order amount should be positive")
    private double amount;

    @NotNull(message = "Payment method should be percised")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Customer should be present")
    @NotEmpty(message = "Customer should be present")
    @NotBlank(message = "Customer should be present")
    private String customerId;

    @NotEmpty(message = "You should at least purchase one product")
    private List<PurchaseRequest> products;

}
