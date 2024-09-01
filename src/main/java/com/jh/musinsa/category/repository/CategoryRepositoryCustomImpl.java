package com.jh.musinsa.category.repository;

import com.jh.musinsa.brand.domain.QBrandEntity;
import com.jh.musinsa.category.domain.QCategoryEntity;
import com.jh.musinsa.category.dto.MaxBrandProductResponse;
import com.jh.musinsa.category.dto.MinBrandProductResponse;
import com.jh.musinsa.product.domain.QProductEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QProductEntity product = QProductEntity.productEntity;
    private final QBrandEntity brand = QBrandEntity.brandEntity;
    private final QCategoryEntity category = QCategoryEntity.categoryEntity;

    /**
     * <pre>
     * SELECT b.name, p1.price
     * FROM product p1
     * JOIN category c ON p1.category_id = c.id
     * JOIN brand b ON p1.brand_id = b.id
     * WHERE c.name = ?
     *  AND p1.price = (
     *          SELECT MIN(p2.price)
     *          FROM product p2
     *          WHERE p1.category_id = p2.category_id
     *  )
     * </pre>
     *
     * @param categoryName
     * @return 카테고리 명에 해당하는 최저 가격과 브랜드명
     */
    @Override
    public List<MinBrandProductResponse> findMinimalPriceBrandProduct(String categoryName) {
        final JPQLQuery<Long> minimumPrice = JPAExpressions
                .select(product.price.min())
                .from(product)
                .where(product.category.id.eq(category.id));

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MinBrandProductResponse.class, brand.name, product.price
                        )
                )
                .from(product)
                .join(product.category, category)
                .join(product.brand, brand)
                .where(category.name.eq(categoryName)
                        .and(product.price.eq(minimumPrice)))
                .fetch();
    }

    /**
     * <pre>
     * SELECT b.name, p1.price
     * FROM product p1
     * JOIN category c ON p1.category_id = c.id
     * JOIN brand b ON p1.brand_id = b.id
     * WHERE c.name = ?
     *  AND p1.price = (
     *          SELECT MAX(p2.price)
     *          FROM product p2
     *          WHERE p1.category_id = p2.category_id
     *  )
     * </pre>
     *
     * @param categoryName
     * @return 카테고리 명에 해당하는 최고 가격과 브랜드명
     */
    @Override
    public List<MaxBrandProductResponse> findMaximalPriceBrandProduct(String categoryName) {
        final JPQLQuery<Long> maximumPrice = JPAExpressions
                .select(product.price.max())
                .from(product)
                .where(product.category.id.eq(category.id));

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                MaxBrandProductResponse.class, brand.name, product.price
                        )
                )
                .from(product)
                .join(product.category, category)
                .join(product.brand, brand)
                .where(category.name.eq(categoryName)
                        .and(product.price.eq(maximumPrice)))
                .fetch();
    }
}
