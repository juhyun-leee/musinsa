package com.jh.musinsa.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    @NotNull(message = "가격은 필수값입니다.")
    @Min(value = 0, message = "가격은 0보다 커야합니다.")
    private long price;
}
