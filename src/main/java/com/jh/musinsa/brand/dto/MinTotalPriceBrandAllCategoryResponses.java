package com.jh.musinsa.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinTotalPriceBrandAllCategoryResponses {
    private String brand;
    private List<MinTotalPriceBrandAllCategoryResponse> categories;
    private long sum;
}
