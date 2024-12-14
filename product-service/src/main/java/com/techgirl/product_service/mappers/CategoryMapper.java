package com.techgirl.product_service.mappers;

import com.techgirl.product_service.models.Category;
import com.techgirl.product_service.models.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toCategoryResponse(Category category) {
       return new CategoryResponse(
               category.getId(),
               category.getName(),
               category.getDescription()
       );
    }
}
