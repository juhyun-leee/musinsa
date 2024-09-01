package com.jh.musinsa.brand.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrandEntityTest {

    @Test
    void 브랜드의_이름을_변경한다() {
        final var actual = new BrandEntity("A");
        final var expected = "Z";

        actual.changeName(expected);

        assertThat(actual.getName()).isEqualTo(expected);
    }

}
