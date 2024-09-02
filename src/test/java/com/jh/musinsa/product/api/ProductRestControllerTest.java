package com.jh.musinsa.product.api;

import com.jh.musinsa.BaseRestControllerTest;
import com.jh.musinsa.product.application.ProductService;
import com.jh.musinsa.product.dto.ProductRegisterRequest;
import com.jh.musinsa.product.dto.ProductUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest extends BaseRestControllerTest {

    @MockBean
    private ProductService service;

    @Test
    void 카테고리별_최저가격_브랜드와_상품가격_총액_조회_API() throws Exception {
        get("/api/v1/products/categories/lowest-prices");
    }

    @Test
    void 상품_추가_API() throws Exception {
        final var requestBody = new ProductRegisterRequest(1L, 1L, 10_000);

        post("/api/v1/products", requestBody);
    }

    @Test
    void 상품_업데이트_API() throws Exception {
        final var requestBody = new ProductUpdateRequest(20_000);

        patch("/api/v1/products/1", requestBody);
    }

    @Test
    void 상품_삭제_API() throws Exception {
        delete("/api/v1/products/1");
    }

}
