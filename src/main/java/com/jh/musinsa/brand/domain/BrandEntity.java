package com.jh.musinsa.brand.domain;

import com.jh.musinsa.global.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "brand")
@NoArgsConstructor
public class BrandEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public BrandEntity(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
