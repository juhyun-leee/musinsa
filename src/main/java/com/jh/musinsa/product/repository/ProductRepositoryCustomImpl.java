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

    /**
     * <pre>
     * SELECT
     *      c.name
     *      b.name,
     *      min(p1.price)
     * FROM product p1
     * JOIN brand b ON b.id = p1.brand_id
     * JOIN category c ON c.id = p1.category_id
     * WHERE p1.price = (
     *          SELECT min(p2.price)
     *          FROM product p2
     *          WHERE p2.category_id = p1.category_id
     * )
     * GROUP BY c.name, b.name
     * </pre>
     *
     * @return 카테고리 별 최저 가격 브랜드명, 카테고리명, 가격
     */
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
