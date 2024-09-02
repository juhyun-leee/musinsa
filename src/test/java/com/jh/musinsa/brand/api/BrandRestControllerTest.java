package com.jh.musinsa.brand.api;

import com.jh.musinsa.BaseRestControllerTest;
import com.jh.musinsa.brand.application.BrandService;
import com.jh.musinsa.brand.dto.BrandRegisterRequest;
import com.jh.musinsa.brand.dto.BrandUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest extends BaseRestControllerTest {

    @MockBean
    private BrandService service;

    @Test
    void 단일_브랜드로_모든_카테고리_상품_구매시_최저가격의_브랜드_카테고리_상품가격_총액_조회_API() throws Exception {
        get("/api/v1/brands/categories/lowest-price");
    }

    @Test
    void 브랜드_추가_API() throws Exception {
        final var requestBody = new BrandRegisterRequest("Z");

        post("/api/v1/brands", requestBody);
    }

    @Test
    void 브랜드_업데이트_API() throws Exception {
        final var requestBody = new BrandUpdateRequest("Z");

        patch("/api/v1/brands/1", requestBody);
    }

    @Test
    void 브랜드_삭제_API() throws Exception {
        delete("/api/v1/brands/1");
    }

}
