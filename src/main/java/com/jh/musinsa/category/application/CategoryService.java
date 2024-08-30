package com.jh.musinsa.category.application;

import com.jh.musinsa.category.dto.MaxBrandProductResponse;
import com.jh.musinsa.category.dto.MinBrandProductResponse;
import com.jh.musinsa.category.dto.MinMaxBrandProductResponses;
import com.jh.musinsa.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public MinMaxBrandProductResponses searchMinMaxPriceBrandProductByCategoryName(String categoryName) {
        final List<MinBrandProductResponse> min = repository.findMinimalPriceBrandProduct(categoryName);
        final List<MaxBrandProductResponse> max = repository.findMaximalPriceBrandProduct(categoryName);

        return new MinMaxBrandProductResponses(categoryName, min, max);
    }
}
