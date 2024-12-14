package com.techgirl.product_service.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
}
