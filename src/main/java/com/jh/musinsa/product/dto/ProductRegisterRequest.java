package com.jh.musinsa.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {
    private long brandId;
    private long categoryId;
    private long price;
}
