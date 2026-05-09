package com.desafio.coupon.api.domain.coupon.exception;

import com.desafio.coupon.api.domain.common.exception.BusinessException;

public class InvalidCouponExpirationDateException extends BusinessException {

    public InvalidCouponExpirationDateException() {
        super("O cupom não pode ser criado com data de expiração no passado.");
    }
}

