package com.jh.musinsa.product.application;

import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponse;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public MinimumPriceByCategoryResponses searchMinimalPriceByCategory() {
        final List<MinimumPriceByCategoryResponse> responses = repository.searchMinimalPriceByCategory();
        final List<MinimumPriceByCategoryResponse> duplicatedResponses = removeDuplicateCategory(responses);
        final long sum = calculateProductSum(duplicatedResponses);

        return new MinimumPriceByCategoryResponses(duplicatedResponses, sum);
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

    private long calculateProductSum(List<MinimumPriceByCategoryResponse> duplicatedResponses) {
        return duplicatedResponses.stream().mapToLong(MinimumPriceByCategoryResponse::getPrice).sum();
    }

    @Transactional
    public void deleteByBrandId(Long brandId) {
        repository.deleteByBrandId(brandId);
    }

}
