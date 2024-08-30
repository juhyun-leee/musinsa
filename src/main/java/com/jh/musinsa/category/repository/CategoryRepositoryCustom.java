package com.jh.musinsa.category.repository;

import com.jh.musinsa.category.dto.MaxBrandProductResponse;
import com.jh.musinsa.category.dto.MinBrandProductResponse;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<MinBrandProductResponse> findMinimalPriceBrandProduct(String categoryName);

    List<MaxBrandProductResponse> findMaximalPriceBrandProduct(String categoryName);
}
