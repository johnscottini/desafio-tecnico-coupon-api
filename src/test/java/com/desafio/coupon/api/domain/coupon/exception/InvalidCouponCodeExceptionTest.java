package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidCouponCodeExceptionTest extends CouponValidationExceptionScenarios {

    @Test
    void testShouldCreateInvalidCouponCodeException() {
        final var builder = shouldCreateInvalidCouponCodeException();

        final InvalidCouponCodeException exception = new InvalidCouponCodeException();

        assertEquals(builder.getExpectedMessage(), exception.getMessage());
    }
}

