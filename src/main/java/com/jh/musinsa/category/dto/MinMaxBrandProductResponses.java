package com.jh.musinsa.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinMaxBrandProductResponses {
    private String category;
    private List<MinBrandProductResponse> minimum;
    private List<MaxBrandProductResponse> maximum;
}
