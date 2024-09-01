package com.jh.musinsa.brand.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandRegisterRequest {

    @NotNull(message = "이름은 필수값입니다.")
    private String name;
}
