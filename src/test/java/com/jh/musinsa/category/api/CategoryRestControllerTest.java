package com.jh.musinsa.category.api;

import com.jh.musinsa.category.application.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @MockBean
    private CategoryService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 카테고리_이름으로_최저_최고_가격의_브랜드와_상품가격_조회_API() throws Exception {
        mockMvc.perform(get("/api/v1/categories/상의"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

}
