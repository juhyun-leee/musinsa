package com.jh.musinsa.product.application;

import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.product.dto.ProductRegisterRequest;
import com.jh.musinsa.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductFacadeService service;

    public MinimumPriceByCategoryResponses searchMinimalPriceByCategory() {
        return service.searchMinimalPriceByCategory();
    }

    @Transactional
    public Long register(ProductRegisterRequest request) {
        return service.register(request);
    }

    @Transactional
    public void update(Long productId, ProductUpdateRequest request) {
        service.update(productId, request);
    }

    @Transactional
    public void delete(Long productId) {
        service.delete(productId);
    }
}
