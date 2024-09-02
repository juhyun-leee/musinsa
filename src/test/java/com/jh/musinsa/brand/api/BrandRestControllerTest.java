package com.jh.musinsa.brand.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.musinsa.brand.application.BrandService;
import com.jh.musinsa.brand.dto.BrandRegisterRequest;
import com.jh.musinsa.brand.dto.BrandUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestController.class)
class BrandRestControllerTest {

    @MockBean
    private BrandService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 단일_브랜드로_모든_카테고리_상품_구매시_최저가격의_브랜드_카테고리_상품가격_총액_조회_API() throws Exception {
        mockMvc.perform(get("/api/v1/brands/categories/lowest-price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void 브랜드_추가_API() throws Exception {
        final var requestBody = new BrandRegisterRequest("Z");

        mockMvc.perform(post("/api/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void 브랜드_업데이트_API() throws Exception {
        final var requestBody = new BrandUpdateRequest("Z");

        mockMvc.perform(patch("/api/v1/brands/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void 브랜드_삭제_API() throws Exception {
        mockMvc.perform(delete("/api/v1/brands/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

}
