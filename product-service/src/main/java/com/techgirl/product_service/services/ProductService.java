package com.techgirl.product_service.services;

import com.techgirl.product_service.exception.ProductPurchaseException;
import com.techgirl.product_service.mappers.ProductMapper;
import com.techgirl.product_service.models.request.ProductPurchaseRequest;
import com.techgirl.product_service.models.request.ProductRequest;
import com.techgirl.product_service.models.response.ProductPurchaseResponse;
import com.techgirl.product_service.models.response.ProductResponse;
import com.techgirl.product_service.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = mapper.toProduct(productRequest);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> purchaseRequest) {
        var productIds = purchaseRequest.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        var storedProducts = repository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exsits");
        }

        var storedRequest = purchaseRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID::" + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);

            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID::" + productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
