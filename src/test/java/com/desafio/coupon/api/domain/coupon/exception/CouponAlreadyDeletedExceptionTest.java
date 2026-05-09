package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponAlreadyDeletedExceptionTest {

    @Test
    void testShouldCreateExceptionWithExpectedMessage() {
        final String code = "00AB12";

        final CouponAlreadyDeletedException exception = new CouponAlreadyDeletedException(code);

        assertEquals("O cupom de código 00AB12 já foi deleteado.", exception.getMessage());
    }
}
