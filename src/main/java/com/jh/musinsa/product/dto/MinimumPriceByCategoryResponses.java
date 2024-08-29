package com.jh.musinsa.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinimumPriceByCategoryResponses {
    private List<MinimumPriceByCategoryResponse> products;
    private long sum;
}
