package com.jh.musinsa.product.api;

import com.jh.musinsa.product.application.ProductService;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.global.common.api.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    // 구현 1) 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    @GetMapping("/categories/min-prices")
    public ResponseEntity<ApiResult<MinimumPriceByCategoryResponses>> searchMinimalPriceByCategory() {

        return ResponseEntity.ok(
                ApiResult.ok(service.searchMinimalPriceByCategory())
        );
    }
}
