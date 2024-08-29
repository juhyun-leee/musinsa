package com.jh.musinsa.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinimumPriceByCategoryResponse {
    private String categoryName;
    private String brandName;
    private long price;
}
