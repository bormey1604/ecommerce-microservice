package com.techgirl.product_service.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private double price;
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;
}
