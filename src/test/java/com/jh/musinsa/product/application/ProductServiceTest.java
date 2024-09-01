package com.jh.musinsa.product.application;

import com.jh.musinsa.global.error.exception.ProductNotFoundException;
import com.jh.musinsa.product.dto.MinimumPriceByCategoryResponses;
import com.jh.musinsa.product.dto.ProductRegisterRequest;
import com.jh.musinsa.product.dto.ProductUpdateRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductServiceTest {
    private final FakeProductFacadeService facadeService = new FakeProductFacadeService();
    private final ProductService service = new ProductService(facadeService);

    @Test
    void 상품을_정상적으로_등록한다() {
        final var request = new ProductRegisterRequest();

        service.register(request);

        assertTrue(facadeService.isCalled());
    }

    @Test
    void 상품명을_정상적으로_변경한다() {
        final var productId = 1L;
        final var request = new ProductUpdateRequest();

        service.update(productId, request);

        assertTrue(facadeService.isCalled());
    }

    @Test
    void 상품명을_변경요청할때_상품이_존재하지_않으면_ProductNotFoundException_예외를_던진다() {
        final var productId = 0L;
        final var request = new ProductUpdateRequest();

        assertThrows(
                ProductNotFoundException.class,
                () -> service.update(productId, request)
        );
    }

    private static class FakeProductFacadeService implements ProductFacadeService {
        private boolean called = false;

        @Override
        public MinimumPriceByCategoryResponses searchMinimalPriceByCategory() {
            return null;
        }

        @Override
        public Long register(ProductRegisterRequest request) {
            this.called = true;

            return 1L;
        }

        @Override
        public void update(Long productId, ProductUpdateRequest request) {
            if (productId == 0L) {
                throw new ProductNotFoundException(productId + "에 해당하는 상품이 존재하지 않습니다.");
            }

            this.called = true;
        }

        @Override
        public void delete(Long productId) {
            this.called = true;
        }

        public boolean isCalled() {
            return this.called;
        }
    }
}
