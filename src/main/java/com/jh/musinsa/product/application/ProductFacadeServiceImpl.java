package com.jh.musinsa.product.application;

import com.jh.musinsa.brand.application.BrandService;
import com.jh.musinsa.brand.domain.BrandEntity;
import com.jh.musinsa.category.application.CategoryService;
import com.jh.musinsa.category.domain.CategoryEntity;
import com.jh.musinsa.global.error.exception.ProductNotFoundException;
import com.jh.musinsa.product.domain.ProductEntity;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponse;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.product.dto.ProductRegisterRequest;
import com.jh.musinsa.product.dto.ProductUpdateRequest;
import com.jh.musinsa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductFacadeServiceImpl implements ProductFacadeService {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductRepository repository;

    @Override
    public MinimumPriceByCategoryResponses searchMinimalPriceByCategory() {
        final List<MinimumPriceByCategoryResponse> duplicatedResponses = repository.findMinimalPriceByCategory();
        final List<MinimumPriceByCategoryResponse> responses = removeDuplicateCategory(duplicatedResponses);
        final long sum = calculateProductSum(responses);

        return new MinimumPriceByCategoryResponses(responses, sum);
    }

    /**
     * 특정 카테고리에 대해 최저 가격이 동일한 브랜드가 존재한다면, 후순위에 존재하는 브랜드를 반환합니다. <br>
     * ex) 스니커즈의 브랜드 A와 G의 가격이 9,000원 이라면, 후순위의 브랜드 G를 반환합니다.
     * @param responses 카테고리 별 최저가격 브랜드와 상품 가격
     * @return 카테고리 별 중복 데이터를 제거한 최저가격 브랜드와 상품 가격
     */
    private List<MinimumPriceByCategoryResponse> removeDuplicateCategory(List<MinimumPriceByCategoryResponse> responses) {
        return responses.stream()
                .collect(
                        collectingAndThen(
                                toCollection(() -> new TreeSet<>(comparing(MinimumPriceByCategoryResponse::getCategoryName))),
                                ArrayList::new)
                );
    }

    private long calculateProductSum(List<MinimumPriceByCategoryResponse> responses) {
        return responses.stream().mapToLong(MinimumPriceByCategoryResponse::getPrice).sum();
    }

    @Override
    public Long register(ProductRegisterRequest request) {
        final BrandEntity brand = brandService.findById(request.getBrandId());
        final CategoryEntity category = categoryService.findById(request.getCategoryId());
        final ProductEntity product = new ProductEntity(request.getPrice(), brand, category);
        final ProductEntity savedProduct = repository.save(product);

        return savedProduct.getId();
    }

    @Override
    public void update(Long productId, ProductUpdateRequest request) {
        final ProductEntity product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId + "에 해당하는 상품이 존재하지 않습니다."));

        product.updatePrice(request.getPrice());
    }

    @Override
    public void delete(Long productId) {
        repository.deleteById(productId);
    }
}
