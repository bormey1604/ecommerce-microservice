package com.techgirl.payment_service.models.requests;

import com.google.gson.Gson;
import com.techgirl.payment_service.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNotificationRequest {
    private String orderReference;
    private double amount;
    private PaymentMethod paymentMethod;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
