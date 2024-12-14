package com.techgirl.product_service.mappers;

import com.techgirl.product_service.models.Category;
import com.techgirl.product_service.models.Product;
import com.techgirl.product_service.models.request.ProductRequest;
import com.techgirl.product_service.models.response.ProductPurchaseResponse;
import com.techgirl.product_service.models.response.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .category(
                        Category.builder()
                                .id(request.getCategoryId())
                                .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
