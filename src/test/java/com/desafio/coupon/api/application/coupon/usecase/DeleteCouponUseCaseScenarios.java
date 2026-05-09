package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.builder.DeleteCouponUseCaseBuilder;
import com.desafio.coupon.api.database.BaseTest;
import org.springframework.stereotype.Component;

@Component
public class DeleteCouponUseCaseScenarios extends BaseTest {

    public DeleteCouponUseCaseBuilder shouldDeleteCoupon() {
        String code = "AB12";

        return DeleteCouponUseCaseBuilder.builder()
                .code(code)
                .build();
    }
}

