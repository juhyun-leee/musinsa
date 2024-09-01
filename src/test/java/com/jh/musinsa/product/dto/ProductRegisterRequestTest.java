package com.jh.musinsa.product.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductRegisterRequestTest {

    private Validator validator;

    @BeforeEach
    void setUP() {
        var validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.afterPropertiesSet();
        this.validator = validatorFactory;
    }

    @Test
    void 필드값이_비어있다면_예외를_던진다() {
        final var request = new ProductRegisterRequest(-1L, 0, 0);
        final var errors = new BeanPropertyBindingResult(request, "productRegisterRequest");

        validator.validate(request, errors);

        assertAll(
                () -> assertThat(errors.getAllErrors().size()).isNotZero(),
                () -> assertThat(errors.hasFieldErrors("brandId")).isTrue(),
                () -> assertThat(errors.getAllErrors().get(0).getDefaultMessage()).isEqualTo("브랜드 ID는 0보다 커야합니다.")
        );
    }

}
