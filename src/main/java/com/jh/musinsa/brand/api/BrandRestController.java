package com.jh.musinsa.brand.api;

import com.jh.musinsa.brand.application.BrandService;
import com.jh.musinsa.brand.dto.BrandRegisterRequest;
import com.jh.musinsa.brand.dto.BrandUpdateRequest;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponses;
import com.jh.musinsa.global.common.api.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // 구현 4) 브랜드 추가 API
    @PostMapping
    public ResponseEntity<ApiResult<Long>> register(
            @RequestBody @Validated BrandRegisterRequest request) {

        return ResponseEntity.ok(
                ApiResult.ok(service.register(request))
        );
    }

    // 구현 4) 브랜드 업데이트 API
    @PatchMapping("/{brandId}")
    public ResponseEntity<ApiResult<Void>> update(
            @PathVariable Long brandId,
            @RequestBody @Validated BrandUpdateRequest request) {
        service.update(brandId, request);

        return ResponseEntity.ok(
                ApiResult.ok()
        );
    }

    // 구현 4) 브랜드 삭제 API
    @DeleteMapping("/{brandId}")
    public ResponseEntity<ApiResult<Void>> delete(@PathVariable Long brandId) {
        service.delete(brandId);

        return ResponseEntity.ok(
                ApiResult.ok()
        );
    }
}
