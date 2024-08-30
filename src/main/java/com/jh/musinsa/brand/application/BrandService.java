package com.jh.musinsa.brand.application;

import com.jh.musinsa.brand.domain.BrandEntity;
import com.jh.musinsa.brand.repository.BrandRepository;
import com.jh.musinsa.global.error.exception.BrandNotFoundException;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponse;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponses;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository repository;

    public MinTotalPriceBrandAllCategoryResponses searchMinimalTotalPriceBrandAllCategory() {
        final MinTotalPriceBrandDto brand = repository.findMinimalTotalPriceBrand();
        final long brandId = brand.getBrandId();
        final BrandEntity brandEntity = repository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId + "에 해당하는 브랜드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        final List<MinTotalPriceBrandAllCategoryResponse> responses = repository.findMinimalTotalPriceBrandAllCategory(brandId);

        return new MinTotalPriceBrandAllCategoryResponses(brandEntity.getName(), responses, brand.getTotalPrice());
    }

}
