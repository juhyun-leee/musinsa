package com.jh.musinsa.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductEntityTest {

    @Test
    void 상품의_가격을_변경한다() {
        final var actual = new ProductEntity();
        final var expected = 10000L;

        actual.updatePrice(expected);

        assertThat(actual.getPrice()).isEqualTo(expected);
    }

}
