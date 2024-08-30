package com.jh.musinsa.brand.repository;

import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponse;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandDto;

import java.util.List;

public interface BrandRepositoryCustom {

    MinTotalPriceBrandDto findMinimalTotalPriceBrand();

    List<MinTotalPriceBrandAllCategoryResponse> findMinimalTotalPriceBrandAllCategory(Long brandId);
}
