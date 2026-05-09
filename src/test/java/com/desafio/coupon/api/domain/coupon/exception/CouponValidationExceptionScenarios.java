package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.builder.CouponValidationExceptionBuilder;
import com.desafio.coupon.api.database.BaseTest;

public abstract class CouponValidationExceptionScenarios extends BaseTest {

    public CouponValidationExceptionBuilder shouldCreateInvalidCouponCodeException() {
        return CouponValidationExceptionBuilder.builder()
                .expectedMessage("O código do cupom não pode ter mais de 6 caracteres.")
                .build();
    }

    public CouponValidationExceptionBuilder shouldCreateInvalidCouponDiscountValueException() {
        return CouponValidationExceptionBuilder.builder()
                .expectedMessage("O valor de desconto precisa ser no mínimo R$0,5.")
                .build();
    }

    public CouponValidationExceptionBuilder shouldCreateInvalidCouponExpirationDateException() {
        return CouponValidationExceptionBuilder.builder()
                .expectedMessage("O cupom não pode ser criado com data de expiração no passado.")
                .build();
    }
}

