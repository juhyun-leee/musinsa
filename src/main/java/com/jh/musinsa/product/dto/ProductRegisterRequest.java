package com.jh.musinsa.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {

    @NotNull(message = "브랜드 ID는 필수값입니다.")
    @Min(value = 0, message = "브랜드 ID는 0보다 커야합니다.")
    private long brandId;

    @NotNull(message = "카테고리 ID는 필수값입니다.")
    @Min(value = 0, message = "카테고리 ID는 0보다 커야합니다.")
    private long categoryId;

    @NotNull(message = "가격은 필수값입니다.")
    @Min(value = 0, message = "가격은 0보다 커야합니다.")
    private long price;
}
