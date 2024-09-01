package com.jh.musinsa.product.domain;

import com.jh.musinsa.brand.domain.BrandEntity;
import com.jh.musinsa.category.domain.CategoryEntity;
import com.jh.musinsa.global.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "product",
        indexes = {
                @Index(name = "idx_category_price", columnList = "category_id, price")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"brand_id", "category_id"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public ProductEntity(long price, BrandEntity brand, CategoryEntity category) {
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
}
