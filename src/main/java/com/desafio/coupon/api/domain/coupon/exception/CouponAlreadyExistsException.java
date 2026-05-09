package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class CouponAlreadyExistsException extends BusinessException {
    public CouponAlreadyExistsException(String code) {
        super(
                String.format("O cupom com código %s já existe.", code)
        );
    }
}
