package com.desafio.coupon.api.domain.coupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidCouponDiscountValueExceptionTest extends CouponValidationExceptionScenarios {

    @Test
    void testShouldCreateInvalidCouponDiscountValueException() {
        final var builder = shouldCreateInvalidCouponDiscountValueException();

        final InvalidCouponDiscountValueException exception = new InvalidCouponDiscountValueException();

        assertEquals(builder.getExpectedMessage(), exception.getMessage());
    }
}

