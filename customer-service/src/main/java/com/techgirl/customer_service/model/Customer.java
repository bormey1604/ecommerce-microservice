package com.techgirl.customer_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer {

    @Id
    private String id;

    @NotNull(message = "customer firstname is required")
    private String firstName;

    @NotNull(message = "customer lastname is required")
    private String lastName;

    @NotNull(message = "customer email is required")
    @Email(message = "customer email is invalid email address")
    private String email;

    private Address address;
}
