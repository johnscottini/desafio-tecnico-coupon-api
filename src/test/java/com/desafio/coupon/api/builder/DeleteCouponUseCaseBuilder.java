package com.desafio.coupon.api.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteCouponUseCaseBuilder {

    private String code;
}

