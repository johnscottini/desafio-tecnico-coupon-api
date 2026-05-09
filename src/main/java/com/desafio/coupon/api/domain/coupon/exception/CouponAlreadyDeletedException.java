package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class CouponAlreadyDeletedException extends BusinessException {

    public CouponAlreadyDeletedException(String couponCode) {
        super(String.format("O coupon de código %s já foi deleteado.", couponCode));
    }
}

