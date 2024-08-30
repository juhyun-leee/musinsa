package com.jh.musinsa.brand.repository;

import com.jh.musinsa.product.dto.MinTotalPriceBrandAllCategoryResponse;
import com.jh.musinsa.product.dto.MinTotalPriceBrandDto;

import java.util.List;

public interface BrandRepositoryCustom {

    MinTotalPriceBrandDto findMinimalTotalPriceBrand();

    List<MinTotalPriceBrandAllCategoryResponse> findMinimalTotalPriceBrandAllCategory(Long brandId);
}
