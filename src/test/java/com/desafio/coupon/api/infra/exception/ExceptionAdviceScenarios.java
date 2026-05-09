package com.desafio.coupon.api.infra.exception;

import com.desafio.coupon.api.builder.ExceptionAdviceBuilder;
import com.desafio.coupon.api.database.BaseTest;

public abstract class ExceptionAdviceScenarios extends BaseTest {

    public ExceptionAdviceBuilder shouldHandleBusinessException() {
        return ExceptionAdviceBuilder.builder()
                .businessMessage("Regra de negócio inválida")
                .path("/api/coupons")
                .build();
    }

    public ExceptionAdviceBuilder shouldHandleGenericException() {
        return ExceptionAdviceBuilder.builder()
                .genericMessage("Erro interno do servidor")
                .path("/api/coupons")
                .build();
    }

    public ExceptionAdviceBuilder shouldHandleValidationException() {
        return ExceptionAdviceBuilder.builder()
                .validationMessage("code é obrigatório")
                .path("/api/coupons")
                .build();
    }
}

