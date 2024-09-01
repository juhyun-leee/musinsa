package com.jh.musinsa.category.application;

import com.jh.musinsa.category.domain.CategoryEntity;
import com.jh.musinsa.category.dto.MaxBrandProductResponse;
import com.jh.musinsa.category.dto.MinBrandProductResponse;
import com.jh.musinsa.category.dto.MinMaxBrandProductResponses;
import com.jh.musinsa.category.repository.CategoryRepository;
import com.jh.musinsa.global.error.exception.CategoryNotFoundException;
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

    public CategoryEntity findById(Long categoryId) {
        return repository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId + "에 해당하는 카테고리가 존재하지 않습니다."));
    }
}
