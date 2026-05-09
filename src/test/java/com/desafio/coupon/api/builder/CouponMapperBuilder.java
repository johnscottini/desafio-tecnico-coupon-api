package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import com.desafio.coupon.api.infra.persistence.coupon.entity.CouponEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponMapperBuilder {

    private CouponEntity entityInput;
    private Coupon domainInput;
    private Coupon expectedDomain;
    private CouponEntity expectedEntity;
}

