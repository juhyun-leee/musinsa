package com.jh.musinsa.brand.api;

import com.jh.musinsa.brand.application.BrandService;
import com.jh.musinsa.global.common.api.ApiResult;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController {

    private final BrandService service;

    public BrandRestController(BrandService service) {
        this.service = service;
    }

    // 구현 2) 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    @GetMapping("/categories/lowest-price")
    public ResponseEntity<ApiResult<MinTotalPriceBrandAllCategoryResponses>> searchMinimalTotalPriceBrandAllCategory() {

        return ResponseEntity.ok(
                ApiResult.ok(service.searchMinimalTotalPriceBrandAllCategory())
        );
    }

}
