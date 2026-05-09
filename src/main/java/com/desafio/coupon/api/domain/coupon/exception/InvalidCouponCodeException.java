package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class InvalidCouponCodeException extends BusinessException {

    public InvalidCouponCodeException() {
        super("O código do cupom não pode ter mais de 6 caracteres.");
    }
}

