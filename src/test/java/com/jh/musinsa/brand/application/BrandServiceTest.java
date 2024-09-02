package com.jh.musinsa.brand.application;

import com.jh.musinsa.brand.domain.BrandEntity;
import com.jh.musinsa.brand.dto.BrandRegisterRequest;
import com.jh.musinsa.brand.dto.BrandUpdateRequest;
import com.jh.musinsa.brand.repository.BrandRepository;
import com.jh.musinsa.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService service;

    @Mock
    private BrandRepository brandRepository;

    private BrandEntity brand;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        brand = Mockito.spy(new BrandEntity());

        lenient().when(brand.getId()).thenReturn(1L);
    }

    @Test
    void 브랜드를_정상적으로_추가한다() {
        final var request = new BrandRegisterRequest("Z");

        when(brandRepository.save(any())).thenReturn(brand);

        final var actual = service.register(request);

        assertAll(
                () -> verify(brandRepository, times(1)).save(any(BrandEntity.class)),
                () -> assertThat(actual).isEqualTo(1L)
        );
    }

    @Test
    void 브랜드명을_정상적으로_변경한다() {
        when(brandRepository.findById(any())).thenReturn(Optional.of(brand));

        final var expected = new BrandUpdateRequest("Z");

        service.update(1L, expected);

        assertThat(brand.getName()).isEqualTo(expected.getName());
    }

    @Test
    void 브랜드_삭제시_연관_상품도_함께_삭제한다() {
        service.delete(1L);

        assertAll(
                () -> verify(productRepository, times(1)).deleteByBrandId(any()),
                () -> verify(brandRepository, times(1)).deleteById(any())
        );
    }

}
