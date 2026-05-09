package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponAlreadyExistsExceptionTest {

    @Test
    void testShouldCreateExceptionWithExpectedMessage() {
        final String code = "00AB12";

        final CouponAlreadyExistsException exception = new CouponAlreadyExistsException(code);

        assertEquals("O cupom com código 00AB12 já existe.", exception.getMessage());
    }
}
