package com.jh.musinsa.brand.repository;

import com.jh.musinsa.brand.domain.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>, BrandRepositoryCustom {
}
