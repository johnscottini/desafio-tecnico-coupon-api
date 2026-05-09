package com.desafio.coupon.api.application.coupon.usecase;

import com.desafio.coupon.api.application.coupon.dto.CouponResponseDto;
import com.desafio.coupon.api.application.coupon.service.CouponService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCouponsUseCase {

    private final CouponService couponService;

    public GetAllCouponsUseCase(CouponService couponService) {
        this.couponService = couponService;
    }

    public List<CouponResponseDto> execute() {
        return couponService.findAllActive();
    }
}

