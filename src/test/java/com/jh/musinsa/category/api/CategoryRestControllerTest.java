package com.jh.musinsa.category.api;

import com.jh.musinsa.BaseRestControllerTest;
import com.jh.musinsa.category.application.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest extends BaseRestControllerTest {

    @MockBean
    private CategoryService service;

    @Test
    void 카테고리_이름으로_최저_최고_가격의_브랜드와_상품가격_조회_API() throws Exception {
        get("/api/v1/categories/상의");
    }

}
