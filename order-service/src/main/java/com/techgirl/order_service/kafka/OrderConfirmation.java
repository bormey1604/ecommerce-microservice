package com.techgirl.order_service.kafka;

import com.google.gson.Gson;
import com.techgirl.order_service.models.responses.CustomerResponse;
import com.techgirl.order_service.models.PaymentMethod;
import com.techgirl.order_service.models.requests.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
    private String orderReference;
    private double totalAmount;
    private PaymentMethod paymentMethod;
    CustomerResponse customer;
    List<PurchaseResponse> products;

    public String toJson (){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
