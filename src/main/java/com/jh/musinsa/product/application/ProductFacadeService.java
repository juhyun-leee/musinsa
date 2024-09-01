package com.jh.musinsa.product.application;

import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.product.dto.ProductRegisterRequest;
import com.jh.musinsa.product.dto.ProductUpdateRequest;

public interface ProductFacadeService {

    MinimumPriceByCategoryResponses searchMinimalPriceByCategory();

    Long register(ProductRegisterRequest request);

    void update(Long productId, ProductUpdateRequest request);

    void delete(Long productId);
}
