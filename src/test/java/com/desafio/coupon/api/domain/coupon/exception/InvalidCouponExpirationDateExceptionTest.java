package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidCouponExpirationDateExceptionTest extends CouponValidationExceptionScenarios {

    @Test
    void testShouldCreateInvalidCouponExpirationDateException() {
        final var builder = shouldCreateInvalidCouponExpirationDateException();

        final InvalidCouponExpirationDateException exception = new InvalidCouponExpirationDateException();

        assertEquals(builder.getExpectedMessage(), exception.getMessage());
    }
}

