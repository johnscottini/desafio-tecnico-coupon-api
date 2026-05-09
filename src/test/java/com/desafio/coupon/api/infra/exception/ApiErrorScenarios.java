package com.desafio.coupon.api.infra.exception;

import com.desafio.coupon.api.builder.ApiErrorBuilder;
import com.desafio.coupon.api.database.BaseTest;

public abstract class ApiErrorScenarios extends BaseTest {

    public ApiErrorBuilder shouldCreateApiError() {
        return ApiErrorBuilder.builder()
                .status(400)
                .message("Mensagem de erro")
                .path("/api/coupons")
                .build();
    }
}

