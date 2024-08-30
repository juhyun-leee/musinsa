package com.jh.musinsa.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinTotalPriceBrandAllCategoryResponse {
    private String category;
    private long price;
}
