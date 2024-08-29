package com.jh.musinsa.product.repository;

import com.jh.musinsa.brand.domain.QBrandEntity;
import com.jh.musinsa.category.domain.QCategoryEntity;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponse;
import com.jh.musinsa.product.domain.QProductEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QProductEntity product = QProductEntity.productEntity;
    private final QBrandEntity brand = QBrandEntity.brandEntity;
    private final QCategoryEntity category = QCategoryEntity.categoryEntity;

    @Override
    public List<MinimumPriceByCategoryResponse> searchMinimalPriceByCategory() {
        final JPQLQuery<Long> priceSubquery = JPAExpressions
                .select(product.price.min())
                .from(product)
                .where(product.category.id.eq(category.id));

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MinimumPriceByCategoryResponse.class, category.name, brand.name, product.price.min()
                        )
                )
                .from(product)
                .join(product.brand, brand)
                .join(product.category, category)
                .where(product.price.eq(priceSubquery))
                .groupBy(category.name, brand.name)
                .fetch();
    }
}
