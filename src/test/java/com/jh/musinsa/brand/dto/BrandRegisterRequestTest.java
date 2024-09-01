package com.jh.musinsa.brand.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BrandRegisterRequestTest {

    private Validator validator;

    @BeforeEach
    void setUP() {
        var validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.afterPropertiesSet();
        this.validator = validatorFactory;
    }

    @Test
    void 필드값이_비어있다면_예외를_던진다() {
        final var request = new BrandRegisterRequest();
        final var errors = new BeanPropertyBindingResult(request, "brandRegisterRequest");

        validator.validate(request, errors);

        assertAll(
                () -> assertThat(errors.getAllErrors().size()).isNotZero(),
                () -> assertThat(errors.hasFieldErrors("name")).isTrue(),
                () -> assertThat(errors.getAllErrors().get(0).getDefaultMessage()).isEqualTo("이름은 필수값입니다.")
        );
    }

}
