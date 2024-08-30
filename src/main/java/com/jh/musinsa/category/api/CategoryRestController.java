package com.jh.musinsa.category.api;

import com.jh.musinsa.category.application.CategoryService;
import com.jh.musinsa.global.common.api.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService service;

    public CategoryRestController(CategoryService service) {
        this.service = service;
    }

    // 구현 3) 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
    @GetMapping("/{categoryName}")
    public ResponseEntity<ApiResult<?>> searchMinMaxPriceBrandProductByCategoryName(@PathVariable String categoryName) {

        return ResponseEntity.ok(
                ApiResult.ok(service.searchMinMaxPriceBrandProductByCategoryName(categoryName))
        );
    }
}
