package com.desafio.coupon.api.builder;

import com.desafio.coupon.api.domain.coupon.model.Coupon;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CouponRepositoryImplBuilder {

    private Coupon couponToSave;
    private Coupon activeCoupon;
    private Coupon deletedCoupon;
    private List<Coupon> activeCoupons;
}

