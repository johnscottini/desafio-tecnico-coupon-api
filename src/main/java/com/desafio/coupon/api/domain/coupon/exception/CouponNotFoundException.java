package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class CouponNotFoundException extends BusinessException {
    public CouponNotFoundException(String code) {
        super(
                String.format("O cupom com código %s não foi encontrado.", code)
        );
    }
}
