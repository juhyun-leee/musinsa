package com.jh.musinsa.brand.application;

import com.jh.musinsa.brand.domain.BrandEntity;
import com.jh.musinsa.brand.dto.BrandRegisterRequest;
import com.jh.musinsa.brand.dto.BrandUpdateRequest;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponse;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponses;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandDto;
import com.jh.musinsa.brand.repository.BrandRepository;
import com.jh.musinsa.global.error.exception.BrandNotFoundException;
import com.jh.musinsa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository repository;
    private final ProductRepository productRepository;

    public MinTotalPriceBrandAllCategoryResponses searchMinimalTotalPriceBrandAllCategory() {
        final MinTotalPriceBrandDto brand = repository.findMinimalTotalPriceBrand();
        final long brandId = brand.getBrandId();
        final BrandEntity brandEntity = findById(brandId);
        final List<MinTotalPriceBrandAllCategoryResponse> responses = repository.findMinimalTotalPriceBrandAllCategory(brandId);

        return new MinTotalPriceBrandAllCategoryResponses(brandEntity.getName(), responses, brand.getTotalPrice());
    }

    @Transactional
    public Long register(BrandRegisterRequest request) {
        final BrandEntity brand = new BrandEntity(request.getName());
        final BrandEntity savedBrand = repository.save(brand);

        return savedBrand.getId();
    }

    @Transactional
    public void update(Long brandId, BrandUpdateRequest request) {
        final BrandEntity brand = findById(brandId);

        brand.changeName(request.getName());
    }

    @Transactional
    public void delete(Long brandId) {
        productRepository.deleteByBrandId(brandId);

        repository.deleteById(brandId);
    }

    public BrandEntity findById(long brandId) {
        return repository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId + "에 해당하는 브랜드가 존재하지 않습니다."));
    }
}
