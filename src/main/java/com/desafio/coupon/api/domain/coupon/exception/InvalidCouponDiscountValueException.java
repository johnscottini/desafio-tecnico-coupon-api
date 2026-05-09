package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class InvalidCouponDiscountValueException extends BusinessException {

    public InvalidCouponDiscountValueException() {
        super("O valor de desconto precisa ser no mínimo R$0,5.");
    }
}

