package com.jh.musinsa.brand.repository;

import com.jh.musinsa.category.domain.QCategoryEntity;
import com.jh.musinsa.product.domain.QProductEntity;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandAllCategoryResponse;
import com.jh.musinsa.brand.dto.MinTotalPriceBrandDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BrandRepositoryCustomImpl implements BrandRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QProductEntity product = QProductEntity.productEntity;
    private final QCategoryEntity category = QCategoryEntity.categoryEntity;

    @Override
    public MinTotalPriceBrandDto findMinimalTotalPriceBrand() {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MinTotalPriceBrandDto.class, product.brand.id, product.price.sum()
                        )
                )
                .from(product)
                .groupBy(product.brand.id)
                .orderBy(product.price.sum().asc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<MinTotalPriceBrandAllCategoryResponse> findMinimalTotalPriceBrandAllCategory(Long brandId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MinTotalPriceBrandAllCategoryResponse.class, category.name, product.price)
                )
                .from(product)
                .join(product.category, category)
                .where(product.brand.id.eq(brandId))
                .fetch();
    }
}
