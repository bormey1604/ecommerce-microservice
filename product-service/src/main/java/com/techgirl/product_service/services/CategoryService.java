package com.techgirl.product_service.services;

import com.techgirl.product_service.mappers.CategoryMapper;
import com.techgirl.product_service.models.response.CategoryResponse;
import com.techgirl.product_service.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryResponse)
                .collect(Collectors.toList()
                );
    }

    public CategoryResponse getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(mapper::toCategoryResponse)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with the ID::" + id));

    }
}
