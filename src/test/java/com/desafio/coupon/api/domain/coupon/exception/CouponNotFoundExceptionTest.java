package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponNotFoundExceptionTest {

    @Test
    void testShouldCreateExceptionWithExpectedMessage() {
        final String code = "00AB12";

        final CouponNotFoundException exception = new CouponNotFoundException(code);

        assertEquals("O cupom com código 00AB12 não foi encontrado.", exception.getMessage());
    }
}
