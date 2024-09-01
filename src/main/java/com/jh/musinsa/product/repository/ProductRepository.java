package com.jh.musinsa.product.repository;

import com.jh.musinsa.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {

    @Modifying
    @Query("DELETE FROM ProductEntity p WHERE p.brand.id = :id")
    void deleteByBrandId(Long id);
}
