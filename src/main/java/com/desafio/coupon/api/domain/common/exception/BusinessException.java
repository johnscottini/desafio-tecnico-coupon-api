package com.desafio.coupon.api.domain.common.exception;

public abstract class BusinessException
        extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}