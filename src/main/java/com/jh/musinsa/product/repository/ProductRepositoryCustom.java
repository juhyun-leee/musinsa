package com.jh.musinsa.product.repository;

import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponse;

import java.util.List;

public interface ProductRepositoryCustom {

    List<MinimumPriceByCategoryResponse> findMinimalPriceByCategory();
}
